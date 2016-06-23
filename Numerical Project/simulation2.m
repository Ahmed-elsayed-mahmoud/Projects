function varargout = simulation2(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @simulation2_OpeningFcn, ...
                   'gui_OutputFcn',  @simulation2_OutputFcn, ...
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



function simulation2_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;
handles.fun = varargin{1};
handles.type = varargin{2};

axes(handles.axes1);

if handles.type == 5  % fixed point
    p = ezplot(@(x) x, [-5, 10]);
    set(p,'Color','red');
    hold on;
    ezplot(handles.fun,[-5, 10]);
    legend('x','g(x)')
    grid on
else
    syms x;
   derivative = inline(diff(handles.fun(x),x),'x');
   derivative
   ezplot(derivative,[-10, 10]);
   legend('f`(x)')
   grid on
end

guidata(hObject, handles);


function varargout = simulation2_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;
