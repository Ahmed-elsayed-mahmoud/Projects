function yi = LagrangeInter(x,y,xi)

n = length(x) - 1; 
ni = length(xi); 

L = ones(n+1,ni); 
for k = 0 : n
    
    for kk = 0 : (k-1)
        L(kk+1,:) = L(kk+1,:).*(xi - x(k+1))/(x(kk+1)-x(k+1)); % see the Lagrange interpolating polynomials
    end 
    
    for kk = k+1 : n 
        L(kk+1,:) = L(kk+1,:).*(xi - x(k+1))/(x(kk+1)-x(k+1)); 
    end 
    
end 

yi = y * L;

