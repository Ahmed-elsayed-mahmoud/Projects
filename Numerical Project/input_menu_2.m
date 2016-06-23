function varargout = input_menu_2(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @input_menu_2_OpeningFcn, ...
                   'gui_OutputFcn',  @input_menu_2_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end

function input_menu_2_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
handles.kind = varargin{1};
guidata(hObject, handles);


function varargout = input_menu_2_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.radiobutton1,'Value');
vv=get(handles.radiobutton2,'Value');
PX = [];
PY = [];
if v ==1
   % enter data
   interpolationData(handles.kind, PX, PY);
elseif vv == 1
   [filename pathname]= uigetfile({'*.txt'},'File Selector');
   fullpathname=strcat (pathname,filename);
   try
   A =read_file( fullpathname);
   catch 
       error_file();
       return;
   end
    len = size(A,2);
    disp(len);
    if(len<2)
        error_validData();
        return;
    end
    st = strsplit(A{1});
    for i=1:size(st,2)
        PX(i) = str2double(st(i));
    end
    st = strsplit(A{2});
    for i=1:size(st,2)
        PY(i) = str2double(st(i));
    end
   interpolationData(handles.kind, PX, PY);
end
delete(input_menu_2(handles.kind));

function pushbutton2_Callback(hObject, eventdata, handles)
main();
delete(input_menu_2(handles.kind));
