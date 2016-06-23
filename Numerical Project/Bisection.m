function [yr,yl,yu,itr,prec,time] = Bisection(fun,xlo, xup, es,maxit)
    if((fun(xlo)==0))
        yr = xlo;
        yl = xlo;
        yu = xup;
        itr = 0;
        prec = 0;
        time = 0;
        return;
    end
    if((fun(xup)==0))
        yr = xup;
        yl = xlo;
        yu = xup;
        itr = 0;
        prec = 0;
        time = 0;
        return;
    end
    if ((fun(xlo)*fun(xup))>0) % if guesses do not bracket, exit
        yr = -1;
        yl = -1;
        yu = -1;
        itr = -1;
        prec = -1;
        time = -1;
        return;
    end
    maxiterations = maxit;
    ea(1) = 100;
    it = 1;
    xl(1) = xlo;
    xu(1) = xup;
    xr(1) = xlo;
    tic; %start stopwatch timer
    while (ea(it) > es && it <= maxiterations)
        it = it+1;
        xr(it)=(xu(it-1)+xl(it-1))/2; % compute the midpoint xr
        
        ea(it) = abs(xr(it)-xr(it-1)); % approx. absolute error
        test= (fun(xl(it-1)) * fun(xr(it))); % compute f(xl)*f(xr)
        if (test < 0) 
            xu(it)=xr(it);
            xl(it) = xl(it-1);
        else
            xl(it)=xr(it);
            xu(it) = xu(it-1);
        end
        if(abs(fun(xr(it)))<=es)
            break;
        end
    end
    time = toc; %get execution time
    yl =xl;
    yu = xu;
    yr = xr;
    itr = it-1;
    prec = ea;
    format long
end