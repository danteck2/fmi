function spline = SplineLin(f, a, b, n, val)
    x = zeros(1, n+1);
    x(1) = a;
    h = (b - a) / n;
    for i = 2 : n+1
        x(i) = x(i - 1) + h;
    end
    
    a = zeros(1, n);
    b = zeros(1, n);
    
    for j = 1 : n
        a(j) = f(x(j));
        b(j) = (f(x(j+1)) - f(x(j))) / (x(j+1) - x(j));
    end
    spline = 0;
    for j = 1 : n
        if val >= x(j) && val <= x(j+1)
            spline = a(j) + b(j) * (val - x(j));
        end
    end
end

