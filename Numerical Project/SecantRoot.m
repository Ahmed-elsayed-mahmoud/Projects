function [Y , iterations, precesion, time ]  = SecantRoot(fun ,Xa ,Xb,Err ,imax)
    tic; %start stopwatch timer
    iterations = 0;
    for i = 1:imax
        iterations = iterations + 1;
        Xi = Xb - fun(Xb)*(Xa-Xb) /(fun(Xa) -fun(Xb)) ;
        Y(i) = Xi;
        precesion(i) = abs(Xi - Xb);
        if precesion(i) < Err
            Xs = Xi;
            break
        end
        Xa = Xb;
        Xb = Xi;
    end
    time = toc; %get execution time
    format long
    %prec = precesion;
end