function varargout = interpolationData(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @interpolationData_OpeningFcn, ...
                   'gui_OutputFcn',  @interpolationData_OutputFcn, ...
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


function interpolationData_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
handles.kind = varargin{1};
handles.PX = varargin{2};
handles.PY = varargin{3};
if size(handles.PX) ~= 0 & size(handles.PY) ~= 0
    data =get(handles.uitable1,'data');
    for i=1: size(handles.PX, 2)
        data(1,i)= handles.PX(i);
        data(2,i)=handles.PY(i);
    end
    set(handles.uitable1,'data',data);
end
guidata(hObject, handles);


function varargout = interpolationData_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles) %add point
data =get(handles.uitable1,'data');
data(:,end+1)=0;
set(handles.uitable1,'data',data);

function pushbutton2_Callback(hObject, eventdata, handles) %add pointX
data =get(handles.uitable2,'data');
data(:,end+1)=0;
set(handles.uitable2,'data',data);


function pushbutton3_Callback(hObject, eventdata, handles) %next
order = get(handles.edit1,'String');  % order
data1 = get(handles.uitable1,'data'); % points
data2 = get(handles.uitable2,'data'); % X
try
X = data1(1,:);
Y = data1(2,:);
px = data2(1,:);
catch
    error_validData();
    return;
end
if handles.kind == 1  %newton
 %   Y=[0 227.4 362.87 517.35 602.97 901.67];
 %   X=[0 10 15 20 22.5 30];
 %   px=[16,17];
    [py, time, fun, Min, Max] = NewtonInter(X, Y, px, str2double(order))
    solution_2(px,py,time,fun,Min, Max,1);
    
elseif handles.kind == 2 %lagrange
    [py, time, fun, Min, Max] = LagrangeInter(X, Y, px,str2double(order))
    solution_2(px,py,time,fun,Min, Max,1);
    disp('yesssss');
end
delete(interpolationData(handles.kind,handles.PX,handles.PY));


function pushbutton4_Callback(hObject, eventdata, handles)%back
main();
delete(interpolationData(handles.kind,handles.PX,handles.PY));


function uitable1_KeyPressFcn(hObject, eventdata, handles)

function uitable2_KeyPressFcn(hObject, eventdata, handles)

function edit1_Callback(hObject, eventdata, handles)

function edit1_CreateFcn(hObject, eventdata, handles)

if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
