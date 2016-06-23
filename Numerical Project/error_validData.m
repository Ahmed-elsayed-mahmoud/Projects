function varargout = error_validData(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @error_validData_OpeningFcn, ...
                   'gui_OutputFcn',  @error_validData_OutputFcn, ...
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

function error_validData_OpeningFcn(hObject, eventdata, handles, varargin)

handles.output = hObject;
colspec=[0 0 0]; 
     set(hObject,'color',colspec);
% Update handles structure
guidata(hObject, handles);


function varargout = error_validData_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;


function pushbutton1_Callback(hObject, eventdata, handles)
delete (error_validData);
