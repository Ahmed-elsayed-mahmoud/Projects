function [yi, time, fun, Min, Max] = NewtonInter(x, y, xi, n)
    [x, y] = sort2D(x, y);
    n = n + 1; % number of point needed
    syms X;   % what is this
    ni = length(xi);
    fun = sym(zeros(1,ni));
    yi = zeros(1,ni);
    ot = zeros(1,n);
    nt = zeros(1,n);
    terms = zeros(1,n);
    Min = zeros(1,ni);
    Max = zeros(1,ni);
    tic;
    for k = 1 : ni
        s = getStart(xi(k), x, n);
        e = s + n - 1;
        for i = s : e
            ot(i - s + 1) = y(i);
        end
        terms(1) = ot(1);
        for i = 1 : e - s
            for j = 1 : e - s + 1 - i
                nt(j) = (ot(j + 1) - ot(j)) / (x(s + j - 1 + i) - x(s + j - 1));
            end
            terms(i + 1) = nt(1);
            ot = nt;
        end
        for i =  0 : n - 1
              p = 1;
              for j = 1 : i
                  p = p .*(X - x(s + j - 1));
              end
              fun(k) = fun(k) + terms(i + 1) * p;
        end
        yi(k) = subs(fun(k) , X , xi(k));
        Min(k) = x(s) - 0.1;
        Max(k) = x(e) + 0.1;
    end
    time = toc;
end

function [nx, ny] = sort2D(x, y)
    nx = x;
    ny = y;
    if issorted(x) == 0
        set(0,'recursionlimit',max(y));
        nx = sort(x);
        n = length(x);
        ny = [];
        for i = 1 : n
            m = find(x == nx(i));
            ny(end+1) = y(m);
        end
    end
end

function l = getStart(v, x, n)
    u = 1;
    len = length(x);
    for i = 1 : len
        u = i;
        if x(i) >= v
         break;
        end
    end
    if u == len
        l = u - n + 1;
    else
        l = u - 1;
        if l ~= 0
            while n ~= 0
                if u < len && abs(x(u)- v) < abs(x(l)- v) %exchange conditions
                    u = u + 1;
                else
                    l = l - 1;
                end
                if l == 0
                    l = 1;
                    break;
                end
                n = n - 1;
            end
        else
            l = 1;
        end
    end
end
