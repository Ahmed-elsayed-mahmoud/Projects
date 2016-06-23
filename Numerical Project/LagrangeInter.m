function [yi, time, fun, Min, Max] = LagrangeInter(x, y, xi, n)
    [x, y] = sort2D(x, y);
    n = n + 1; % number of point needed
    syms X;
    ni = length(xi);
    fun = sym(zeros(1,ni));
    yi = zeros(1,ni);
    Min = zeros(1,ni);
    Max = zeros(1,ni);
    tic;
    for k = 1 : ni
        s = getStart(xi(k), x, n);
        e = s + n - 1;
        for i =  s : e
              l = 1;
              for j = s : e
                  if j ~= i
                   l = l .* ((X - x(j)) / (x(i) - x(j)));
                  end
              end
              fun(k) = fun(k) + l * y(i);
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
        if x(i) > v
         break;
        end
    end
    if u == len
        l = u - n + 1;
    else
        l = u - 1;
        if l ~= 0
            while n ~= 0
                if abs(x(u)- v) < abs(x(l)- v) && u < len
                    u = u + 1;
                else
                    l = l - 1;
                end
                if l == 0
                    break;
                end
                n = n - 1;
            end
        end
        l = l + 1;
    end
end
