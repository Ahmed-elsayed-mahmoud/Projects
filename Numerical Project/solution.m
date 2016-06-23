function varargout = solution(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @solution_OpeningFcn, ...
                   'gui_OutputFcn',  @solution_OutputFcn, ...
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


function solution_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);


handles.xx=varargin{1};
handles.xl=varargin{2};
handles.xu=varargin{3};
handles.iteration=varargin{4};
handles.perc=varargin{5};
handles.time=varargin{6};
handles.fun = varargin{7};
handles.type = varargin{8};

data =get(handles.uitable1,'data');

if handles.iteration == -1
    error_noRoots();
    return;
end

for i=1: handles.iteration
    data(i,1)= handles.xx(i);
    data(i,2)=handles.perc(i);
end
set(handles.uitable1,'data',data);
set(handles.edit1,'String',handles.iteration);
set(handles.edit2,'String',handles.time);
set(handles.edit3,'String',handles.xx(handles.iteration));

% Update handles structure
guidata(hObject, handles);


function varargout = solution_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;



function edit1_Callback(hObject, eventdata, handles)
 

function edit1_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit2_Callback(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

function edit2_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

function pushbutton1_Callback(hObject, eventdata, handles)
delete(solution(handles.xx,handles.xl,handles.xu,handles.iteration,handles.perc,handles.time,handles.fun, handles.type));


function pushbutton2_Callback(hObject, eventdata, handles)
main();
delete(solution(handles.xx,handles.xl,handles.xu,handles.iteration,handles.perc,handles.time,handles.fun, handles.type));


function edit3_Callback(hObject, eventdata, handles)


function edit3_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function pushbutton4_Callback(hObject, eventdata, handles)
if size(handles.xu) ~= 0
    xyz = handles.xx(2:size(handles.xx,2));
    simulation(handles.xl,handles.xu,xyz,1,handles.fun);
else
    simulation2(handles.fun, handles.type);
end
