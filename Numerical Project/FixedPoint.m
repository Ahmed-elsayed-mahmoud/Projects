function [Y,iter,precision,time] = FixedPoint(g,x0,accuracy,maxIterations)
except = MException('FIXEDPOINT:BADGUESS','Diverge');
x(1) = x0;
xr = x0;
Y(1) = x0;
iter = 0;
tic;
for i = 1 : maxIterations
    xrold = xr;
    xr = g(xrold);
    Y(i+1) = xr;
    iter = iter + 1;
    if xr  ~= 0
        precision(i) = abs(xr-xrold);
    end 
    if precision(i) < accuracy
        break;
    end
    if i > 20
        if precision(i) > precision(i-1)
            throw(except);
        end
    end
end
time = toc;
format long;
end