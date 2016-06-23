function [y,xl,xu,itr,prec,time,equation, type] = PartA( A )
    xl = [];
    xu = [];
    len = size(A,2);
    disp(len);
    if(len<2)
        y = -1;
        itr = -1;
        prec = -1;
        time = -1;
        return;
    end
    equation = A{1};
    equation = strcat('@(x) ',equation);
    try equation = str2func(equation);
    catch 
        y = -1;
        itr = -1;
        prec = -1;
        time = -1;
        return;
    end
    try equation(1)
    catch 
        y = -1;
        itr = -1;
        prec = -1;
        time = -1;
        return;
    end
    st = strsplit(A{2});
    len = size(st,2);
    try
    type = str2double(st(1));
    if (type == 1)
        if(len==3)
            st(3)
            [y,xl,xu,itr,prec,time] = Bisection(equation,str2double(st(2)),str2double(st(3)),10^-5,50);
        elseif(len==4)
            st(4)
            [y,xl,xu,itr,prec,time] = Bisection(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),50);
        elseif(len==5)
            st(5)
             [y,xl,xu,itr,prec,time] = Bisection(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),str2double(st(5)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    elseif(type ==2)
         % regula falsi
         if(len==3)
            [y,itr,prec,time] = FalsePosition(equation,str2double(st(2)),str2double(st(3)),10^-5,50);
        elseif(len==4)
            [y,itr,prec,time] = FalsePosition(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),50);
        elseif(len==5)
             [y,itr,prec,time] = FalsePosition(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),str2double(st(5)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    elseif(type ==3)
        %fixed point
        if(len==2)
            [y,itr,prec,time] = FixedPoint(equation,str2double(st(2)),10^-5,50);
        elseif(len==3)
            [y,itr,prec,time] = FixedPoint(equation,str2double(st(2)),str2double(st(3)),50);
        elseif(len==4)
             [y,itr,prec,time] = FixedPoint(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    elseif(type ==4)
        % newton raphson
        if(len==2)
            [y,itr,prec,time] = newton_raphson(equation,str2double(st(2)),10^-5,50);
        elseif(len==3)
            [y,itr,prec,time] = newton_raphson(equation,str2double(st(2)),str2double(st(3)),50);
        elseif(len==4)
             [y,itr,prec,time] = newton_raphson(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    elseif(type ==5)
        % secant
         if(len==3)
            [y,itr,prec,time] = SecantRoot(equation,str2double(st(2)),str2double(st(3)),10^-5,50);
        elseif(len==4)
            [y,itr,prec,time] = SecantRoot(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),50);
        elseif(len==5)
             [y,itr,prec,time] = SecantRoot(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)),str2double(st(5)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    elseif(type ==6)
        % Bierge vieta
        if(len==2)
            [y,itr,prec,time] = bierge_vieta(equation,str2double(st(2)),10^-5,50);
        elseif(len==3)
            [y,itr,prec,time] = bierge_vieta(equation,str2double(st(2)),str2double(st(3)),50);
        elseif(len==4)
             [y,itr,prec,time] = bierge_vieta(equation,str2double(st(2)),str2double(st(3)),str2double(st(4)));
        else
            y = -1;
            itr = -1;
            prec = -1;
            time = -1;
        end
    else
        y = -1;
        itr = -1;
        prec = -1;
        time = -1; 
    end
    catch
         y = -1;
        itr = -1;
        prec = -1;
        time = -1;
    end
end

