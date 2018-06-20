B = [1 1;0 1];
C = inv(B);
r = 2;
A = [2;3]

Bt = B
Bt(:,r)=A

Y=C*A

if Y(r) ~= 0
    disp('Bt e inversabila');

    I = eye(size(B,1));

    for i= 1:size(B,1)
        if i ~= r
            I(i,r) = -Y(i)/Y(r);
        elseif i==r
            I(i,r) = 1/Y(r);
        end;
    end;

    Rez=I*C
    Verif=Bt * Rez
else
    disp('Bt nu e inversabila');
end;
  