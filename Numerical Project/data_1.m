
function varargout = data_1(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @data_1_OpeningFcn, ...
                   'gui_OutputFcn',  @data_1_OutputFcn, ...
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


function data_1_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
colspec=[0 0 0]; 
set(hObject,'color',colspec);
handles.fun=varargin{1};
handles.var=varargin{2};
handles.kind=varargin{3};
% Update handles structure
guidata(hObject, handles);

function varargout = data_1_OutputFcn(hObject, eventdata, handles) 
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



function edit4_Callback(hObject, eventdata, handles)


function edit4_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


function pushbutton1_Callback(hObject, eventdata, handles)
v=get(handles.edit1,'String'); %xl
vv=get(handles.edit2,'String');%xu
vvv=get(handles.edit3,'String');%max iter
vvvv=get(handles.edit4,'String'); %accu

if isempty(v) || isempty(vv)
    error_validData();
    return;
end
if isempty(vvv)
    vvv='50';
end
if isempty(vvvv)
    vvvv='0.00001';
end
x1=str2double(v); %xl
x2=str2double(vv);%xu
x3=str2double(vvv);%max iter
x4=str2double(vvvv);%accur 
all= strcat('@(',handles.var,')',handles.fun); %function from input_func
try
fun=str2func (all);
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

if handles.kind==1
    %bisection
    [yr,yl,yu,itr,prec,time]= Bisection(fun,x1,x2,x4,x3); 
    solution(yr,yl,yu,itr,prec,time, fun,1);

elseif handles.kind==3
    %falseposition
   [a,xl, xu,b,c,d]= FalsePosition(fun,x1,x2, x4,x3);
   solution(a,xl,xu,b,c,d,fun,3);

elseif handles.kind==4
    %secant
  [a,b,c,d]=  SecantRoot (fun,x1,x2, x4,x3);
   xl = [];
   xu = [];
 solution(a,xl,xu,b,c,d,fun,4);

end
delete(data_1(handles.fun ,handles.var,handles.kind));
    

function pushbutton2_Callback(hObject, eventdata, handles)
main();
delete(data_1(handles.fun ,handles.var,handles.kind));
