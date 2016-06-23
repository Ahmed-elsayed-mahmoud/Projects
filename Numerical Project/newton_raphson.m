function [Y,iterations,precesion,time] = newton_raphson(myfunction,X0,accuracy,maxIterations)
    xi = X0;
    Y = X0;
    precesion = []; 
    iterations = 0;
    syms x;
    derivative = inline(diff(myfunction(x),x),'x');
    ae = accuracy;
    tic;
    for j = 1 : maxIterations
        xi = xi - (myfunction(xi) / derivative(xi));
        Y(end + 1) = xi;
        iterations = iterations + 1;
        precesion(end + 1) = abs(Y(end) - Y(end - 1));
        if precesion(end) < ae
            break
        end
    end
    time = toc;
end