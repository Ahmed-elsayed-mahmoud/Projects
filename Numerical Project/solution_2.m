function varargout = solution_2(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @solution_2_OpeningFcn, ...
                   'gui_OutputFcn',  @solution_2_OutputFcn, ...
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


function solution_2_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;

axes(handles.axes1);

handles.dataX = varargin{1};
handles.dataY = varargin{2};
handles.time = varargin{3};
handles.equation = varargin{4};
handles.Min = varargin{5};
handles.Max = varargin{6};
handles.pos = varargin{7};

data =get(handles.uitable1,'data');
set(handles.edit1,'String',char(handles.equation(handles.pos)));
set(handles.edit2,'String',handles.time);

%for i=1: size(handles.dataX, 2)
    data(1,1)= handles.dataX(handles.pos);
    data(2,1)=handles.dataY(handles.pos);
%end
set(handles.uitable1,'data',data);
ezplot(handles.equation(handles.pos),[handles.Min(handles.pos),handles.Max(handles.pos)]);
legend('equation')
%plot(handles.range,handles.equation);
%ezplot(handles.equation, handles.range);
%hold on;

guidata(hObject, handles);


function varargout = solution_2_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;



function edit1_Callback(hObject, eventdata, handles)


function edit1_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function edit2_Callback(hObject, eventdata, handles)

function edit2_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


function pushbutton1_Callback(hObject, eventdata, handles)
if handles.pos < size(handles.equation,2)
    delete(solution_2(handles.dataX,handles.dataY,handles.time,handles.equation,handles.Min,handles.Max,(handles.pos)));
    solution_2(handles.dataX,handles.dataY,handles.time,handles.equation,handles.Min,handles.Max,(handles.pos+1));
end

function pushbutton2_Callback(hObject, eventdata, handles)
if handles.pos >1
    delete(solution_2(handles.dataX,handles.dataY,handles.time,handles.equation,handles.Min,handles.Max,(handles.pos)));
    solution_2(handles.dataX,handles.dataY,handles.time,handles.equation,handles.Min,handles.Max,(handles.pos-1));
else
    main();
    delete(solution_2(handles.dataX,handles.dataY,handles.time,handles.equation,handles.Min,handles.Max,(handles.pos)));
end



function uitable1_DeleteFcn(hObject, eventdata, handles)
