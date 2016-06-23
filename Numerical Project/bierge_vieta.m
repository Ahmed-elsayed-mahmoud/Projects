function [Y,iterations,precesion,time] = bierge_vieta(myfunction,X0,accuracy,maxIterations)
    syms x;
    coeff = sym2poly(sym(myfunction));
    xi = X0;
    Y = X0;
    precesion = []; 
    iterations = 0;
    ae = accuracy;
    tic;
    for j = 1 : maxIterations
        b = coeff(1);
        c = coeff(1);
        for i = 2 : length(coeff)
            b = coeff(i) + b * xi;
            if i ~= length(coeff)
                c = b + c * xi;
            end
        end
        xi = xi - b/c;
        Y(end + 1) = xi;
        iterations = iterations + 1;
        precesion(end + 1) = abs(Y(end) - Y(end - 1));
        if precesion(end) < ae
            break
        end
    end
    time = toc;
    format long
end
