function varargout = simulation(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @simulation_OpeningFcn, ...
                   'gui_OutputFcn',  @simulation_OutputFcn, ...
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



function simulation_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
handles.arrXL = varargin{1};
handles.arrXU = varargin{2};
handles.arrXR = varargin{3};
handles.pos = varargin{4};
handles.fun = varargin{5};

xu = (handles.arrXU(1))
xl = (handles.arrXL(1))
begin = min(xu,xl)-5
endd = max(xu,xl)+5
handles.fun
fxu = handles.fun(begin)
fxl = handles.fun(endd)
 
axes(handles.axes1);
ezplot(handles.fun,[begin, endd]); 
grid on;
hold on;
XU = [handles.arrXU(handles.pos), handles.arrXU(handles.pos)];
YU = [min(fxu,fxl)-5, max(fxu,fxl)+5];
handles.PU = plot(XU,YU,'color','r');

XL = [handles.arrXL(handles.pos), handles.arrXL(handles.pos)];
YL = [min(fxu,fxl)-5, max(fxu,fxl)+5];
handles.PL = plot(XL,YL,'color','g');

XR = [handles.arrXR(handles.pos), handles.arrXR(handles.pos)];
YR = [min(fxu,fxl)-5, max(fxu,fxl)+5];
handles.PR = plot(XR,YR,'color','b');
legend('XR','XU','XL')
guidata(hObject, handles);


function varargout = simulation_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles)
 if handles.pos < size(handles.arrXU,2)-1 
     delete(simulation(handles.arrXL, handles.arrXU, handles.arrXR, handles.pos, handles.fun));
     simulation(handles.arrXL, handles.arrXU, handles.arrXR, (handles.pos+1), handles.fun)
 else
     delete(simulation(handles.arrXL, handles.arrXU, handles.arrXR, handles.pos, handles.fun));
 end
