function [ DInv ] = LemaSubts( B, r, A, BInv )
    Y = BInv * A;
    s = size(B);
    m = s(1);
    if Y(r) == 0
        printf('D nu este inversabila');
    else
        for k = 1:m
            if k == r
                e(k) = 1 / Y(r);
            else
               e(k) = - Y(k) / Y(r); 
            end
        end
    end
    
    E = eye(m);
    E(:,r) = e;
    
    DInv = E * BInv;
end

