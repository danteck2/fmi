

%A=[1 2 3; 0 1 4; 5 2 1];
A=[3 0 1; 0 3 0; 0 0 1];

det=determinant(A,0);
det

function rez = determinant(a, temp)
    n = size(a,1);
    if n == 2
        temp = a(1,1) * a(2,2) - a(1,2) * a(2,1);
    else
    for i = 1 : n
        minor = a;
        minor(1,:) = [];
        minor(:,i) = [];
        temp = temp + a(1,i) * (-1)^(1+i) * determinant(minor,0);
    end
    end
    rez=temp;
end


