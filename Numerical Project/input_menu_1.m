function varargout = input_menu_1(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @input_menu_1_OpeningFcn, ...
                   'gui_OutputFcn',  @input_menu_1_OutputFcn, ...
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


function input_menu_1_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
guidata(hObject, handles);


function varargout = input_menu_1_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.radiobutton1,'Value');
vv=get(handles.radiobutton2,'Value');
if v ==1
   input_func();
elseif vv == 1
   [filename pathname]= uigetfile({'*.txt'},'File Selector');
   fullpathname=strcat (pathname,filename);
   try
   A = read_file( fullpathname);
   
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
    equation = A{1};
    var = A{2};
    all= strcat('@(',var,')',equation);
    try fun = str2func(all);
    catch 
        disp('heee');
        all
        error_validData();
        return;
    end
    try fun(1)
    catch 
       error_validData();
        return;
    end
    methods_menu(equation,var);
end
delete(input_menu_1);


function pushbutton2_Callback(hObject, eventdata, handles)
main();
delete(input_menu_1);
