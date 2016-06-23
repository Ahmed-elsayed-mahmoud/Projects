function varargout = data_2(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @data_2_OpeningFcn, ...
                   'gui_OutputFcn',  @data_2_OutputFcn, ...
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

function data_2_OpeningFcn(hObject, eventdata, handles, varargin)
colspec=[0 0 0]; 
     set(hObject,'color',colspec);

handles.output = hObject;
handles.fun=varargin{1};
handles.var=varargin{2};
handles.kind=varargin{3};


guidata(hObject, handles);



function varargout = data_2_OutputFcn(hObject, eventdata, handles) 
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


function edit3_Callback(hObject, eventdata, handles)

function edit3_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.edit1,'String'); %guess
vv=get(handles.edit2,'String'); %max iter
vvv=get(handles.edit3,'String');%accu
length(v)
if length(v)==0 
    error_validData;
end

if length(vv)==0
    vv='50';
end
if length(vvv)==0
    vvv='0.00001';
end
x1=str2double(v); %guess
x2=str2double(vv); %max iter
x3=str2double(vvv);%accur
all= strcat('@(',handles.var,')',handles.fun);

try
   fun = str2func (all);
catch 
   error_validData();
   return;
end

try
   fun(1);
catch 
   error_validData();
   return;
end
   xl = [];
   xu = [];
   
if handles.kind==2
    %newton
  [a,b,c,d]=  newton_raphson(fun,x1,x3,x2);
  solution(a,xl,xu, b,c,d,fun, 2);

elseif handles.kind==5
    %fixed point
    all= strcat('@(',handles.var,')',handles.fun,'+',handles.var );
    fun = str2func (all);
    disp('hereeee');
    fun
    try
    [a,b,c,d]= FixedPoint(fun,x1,x3,x2);
    
    catch
        error_noRoots();
        return;
    end
   solution(a,xl,xu, b,c,d,fun, 5);
elseif handles.kind==6
    %bierge vieta
    [a,b,c,d]=  bierge_vieta(handles.fun,x1,x3,x2);
    solution(a,xl,xu, b,c,d,fun, 6);
    
end
delete(data_2(handles.fun ,handles.var,handles.kind));

function pushbutton2_Callback(hObject, eventdata, handles)
main();        
delete(data_2(handles.fun ,handles.var,handles.kind));
