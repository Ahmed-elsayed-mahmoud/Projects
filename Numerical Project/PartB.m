function [px,py,time,fun, Min, Max] = PartB( A )

    len = size(A,2);
    disp(len);
    if(len<5)
        time = -1;
        return;
    end
    
    st = strsplit(A{1});
    len = size(st,2);
    try
    type = str2double(st(1));
    if (type == 1) %newton
        st = strsplit(A{2});
        order = str2double(st(1));
        st = strsplit(A{3});
        for i=1:size(st,2)
            X(i) = str2double(st(i));
        end
        st = strsplit(A{4});
        for i=1:size(st,2)
            Y(i) = str2double(st(i));
        end
        st = strsplit(A{5});
        for i=1:size(st,2)
            px(i) = str2double(st(i));
        end
        [py, time, fun, Min, Max] = NewtonInter(X, Y, px, order)
        
    elseif(type ==2) %lagrange
        disp('herr');
         st = strsplit(A{2});
        order = str2double(st(1));
        st = strsplit(A{3});
        for i=1:size(st,2)
            X(i) = str2double(st(i));
        end
        st = strsplit(A{4});
        for i=1:size(st,2)
            Y(i) = str2double(st(i));
        end
        st = strsplit(A{5});
        for i=1:size(st,2)
            px(i) = str2double(st(i));
        end
        order
        X
        Y
        px
        [py, time, fun, Min, Max] = LagrangeInter(X, Y, px, order)
    else
        time = -1; 
    end
    catch
        time = -1;
    end
end

