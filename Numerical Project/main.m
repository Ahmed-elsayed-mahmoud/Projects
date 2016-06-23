function varargout = main(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @main_OpeningFcn, ...
                   'gui_OutputFcn',  @main_OutputFcn, ...
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


function main_OpeningFcn(hObject, eventdata, handles, varargin)

handles.output = hObject;
 colspec=[0 0 0]; 
     set(hObject,'color',colspec);

guidata(hObject, handles);



function varargout = main_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.part_1,'Value');
vv=get(handles.part_2,'Value');
if v ==1
   input_menu_1();
elseif vv == 1
   methods_menu_2();
end
delete(main);
