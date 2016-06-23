function varargout = methods_menu_2(varargin)

gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @methods_menu_2_OpeningFcn, ...
                   'gui_OutputFcn',  @methods_menu_2_OutputFcn, ...
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


function methods_menu_2_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
guidata(hObject, handles);


function varargout = methods_menu_2_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.radiobutton1,'Value');
vv=get(handles.radiobutton2,'Value');

handles.kind=0;

if v == 1
    handles.kind=1; %Newton
    input_menu_2(handles.kind);
   
elseif vv == 1
    handles.kind=2;%lagrange
    input_menu_2(handles.kind);

end
delete(methods_menu_2());


% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
 main;
delete(methods_menu_2());


function edit1_Callback(hObject, eventdata, handles)

function edit1_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function radiobutton1_Callback(hObject, eventdata, handles)


function radiobutton2_Callback(hObject, eventdata, handles)
