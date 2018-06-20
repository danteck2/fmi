function spline = SplinePatratic(f, fd, a, b, n, val)
    x = zeros(1, n+1);
    x(1) = a;
    h = (b-a) / n;
    for i = 2 : n+1
        x(i) = x(i - 1) + h;
    end
    
    a = zeros(1, n);
    b = zeros(1, n);
    c = zeros(1, n);
    
    b(1) = fd(x(1));
    for j = 1 : n-1
        a(j) = f(x(j));
        b(j+1) = -b(j) + (2 / h) * (f(x(j+1)) - f(x(j)));
        c(j) = (1 / h^2) * (f(x(j+1)) - f(x(j))) - h * b(j);
    end
    j = n;
    a(j) = f(x(j));
    c(j) = (1 / h^2) * (f(x(j+1)) - f(x(j))) - h * b(j);
    
    spline = 0;
    for j = 1 : n
        if ((val >= x(j)) && (val <= x(j+1)))
            spline = a(j) + b(j) * (val - x(j)) + c(j) * (val - x(j)) ^ 2;
        end
    end
end
