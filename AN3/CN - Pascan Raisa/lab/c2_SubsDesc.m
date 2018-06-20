function [x]=SubsDesc(A,b)
n=length(b);
x(n)=b(n)/A(n,n);
k=n-1;
while k>0
    sum=0;
    for j=k+1:n
        sum=sum+A(k,j)*x(j);
    end
    x(k)=1/A(k,k)*(b(k)-sum);
    k=k-1;
end
