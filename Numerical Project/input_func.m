function varargout = input_func(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @input_func_OpeningFcn, ...
                   'gui_OutputFcn',  @input_func_OutputFcn, ...
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


function input_func_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
% Update handles structure
guidata(hObject, handles);


function varargout = input_func_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;



function edit1_Callback(hObject, eventdata, handles)


function edit1_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

function pushbutton1_Callback(hObject, eventdata, handles)
fun=get (handles.edit1,'String')
var=get (handles.edit2,'String')
length(fun)
length(var)
if length(fun)==0 || length(var)~=1
    error_validData();
else
methods_menu(fun,var);
delete(input_func);
end


function pushbutton2_Callback(hObject, eventdata, handles)
main();
delete(input_func);

function edit2_Callback(hObject, eventdata, handles)

function edit2_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
