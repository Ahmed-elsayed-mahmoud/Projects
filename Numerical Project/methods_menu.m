function varargout = methods_menu(varargin)

gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @methods_menu_OpeningFcn, ...
                   'gui_OutputFcn',  @methods_menu_OutputFcn, ...
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


function methods_menu_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
handles.fun=varargin{1};
handles.var=varargin{2};
guidata(hObject, handles);


function varargout = methods_menu_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.radiobutton1,'Value');
vv=get(handles.radiobutton2,'Value');
vvv=get(handles.radiobutton3,'Value');
vvvv=get(handles.radiobutton4,'Value');
vvvvv=get(handles.radiobutton5,'Value');
vvvvvv=get(handles.radiobutton6,'Value');
handles.kind=0;

if v == 1
    handles.kind=1; %bisection
    data_1(handles.fun,handles.var,handles.kind);
elseif vv == 1
    handles.kind=2;%newton raphson
    data_2(handles.fun,handles.var,handles.kind);
elseif vvv == 1
    handles.kind=3;%false position
    data_1(handles.fun,handles.var,handles.kind);
elseif vvvv == 1
    handles.kind=4;%secant
    data_1(handles.fun,handles.var,handles.kind);
elseif vvvvv == 1
    handles.kind=5;%fixed point
    data_2(handles.fun,handles.var,handles.kind);
elseif vvvvvv == 1
    handles.kind=6;%bierge vieta
    data_2(handles.fun,handles.var,handles.kind);
end

delete(methods_menu(handles.fun,handles.var));


% --- Executes on button press in pushbutton2.
function pushbutton2_Callback(hObject, eventdata, handles)
 main;
delete(methods_menu(handles.fun,handles.var));


function edit1_Callback(hObject, eventdata, handles)

function edit1_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function radiobutton1_Callback(hObject, eventdata, handles)


function radiobutton2_Callback(hObject, eventdata, handles)

function radiobutton3_Callback(hObject, eventdata, handles)

function radiobutton4_Callback(hObject, eventdata, handles)

function radiobutton5_Callback(hObject, eventdata, handles)

function radiobutton6_Callback(hObject, eventdata, handles)
