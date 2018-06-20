function [x]=GaussPivTot(A,b)
A=[A,b];% Matricea extinsa
n=size(A,1);
xindice=1:n;%Numerotare initiala necunoscute
for k=1:n-1
    %Calcul maxim
    max=abs(A(k,k)); 
    p = k; m = k; 
    for i  = k:n
        for j = k:n
            if abs(A(i,j))> max
                max=abs(A(i,j));
                p = i; m = j;
            end
        end
    end
    if A(p,m) == 0 
        display('Sist. incomp. sau comp. nedet.');
        break
    end
    
    if p~=k
       A([p,k],:) = A([k,p],:);
    end
    if m~=k
        A(:,[m,k]) = A(:,[k,m]);
        xindice([m,k]) = xindice([k,m]);  %Schimbare indici necunoscute
    end
    for l=k+1:n
        A(l,:) = A(l,:)-A(l,k)/A(k,k)*A(k,:);
    end
end
if A(n,n) == 0
    display('Sist. incomp. sau comp. nedet.')
end
xschimbat=SubsDesc(A(1:n,1:n),A(:,n+1)); 
for i = 1:n
    x(xindice(i)) = xschimbat(i); %Revenire la indicii initiali
end