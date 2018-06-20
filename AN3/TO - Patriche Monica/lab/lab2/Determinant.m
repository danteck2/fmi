function [ D ] = Determinant( B )
    s = size(B);
    m = s(1,1);
    
    if (s == 1)
        D = B(1,1);
    else
        sumTmp = 0;
        for k = 1 : m
            Bc = B;
            Bc(1,:) = [];
            Bc(:, k) = [];
            d = Determinant(Bc);
            sumTmp = sumTmp +  B(1,k) * (-1)^(1 + k) * d;
        end
        
        D = sumTmp;
    end
end

