
function [yr,yl,yu,itr,prec,time] = FalsePosition(fun,xlo, xup, es,maxit)
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
    if (fun(xlo)*fun(xup)>0) % if guesses do not bracket, exit
        yr = -1;
        itr =-1;
        prec=-1;
        time = -1;
        return
    end
    xl(1) = xlo;
    xu(1) = xup;
    ea(1) = 100;
    maxiterations = maxit;
    it = 1;
    tic;
    xr(1) = xlo;
    while (ea(it) > es)&&(it<=maxiterations)
        it = it+1;
        fu = fun(xu(it-1));
        fl = fun(xl(it-1));
        xr(it)=(xl(it-1)*fu-xu(it-1)*fl)/(fu-fl); 
        ea(it) = abs(xr(it)-xr(it-1)); 
        test= (fun(xl(it-1)) * fun(xr(it))); 
        if (test < 0) 
            xu(it)=xr(it);
            xl(it)=xl(it-1);
        else
            xl(it)=xr(it);
            xu(it)=xu(it-1);
        end
         if(abs(fun(xr(it)))<=es)
            break;
        end
    end
    time = toc;
    itr = it-1;
    yl = xl;
    yr = xr;
    yu = xu;
    prec = ea;
    format long;
end