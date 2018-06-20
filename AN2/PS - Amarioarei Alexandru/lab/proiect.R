#Proiect 1

#Ex 1 media si varianta 1000 v.a. independente

p = rpois(1000,500)
b = rbinom(1000, 10, 0.1)
e = rexp(1000,10)
n = rnorm(1000,500)
print(var(p))
print(mean(p))
print(var(b))
print(mean(b))
print(var(e))
print(mean(e))
print(var(n))
print(mean(n))

#Ex 2 si 3 graficele functiilor de masa, densitate si functiile de repartitie

##graficele poisson
p1 = data.frame(DENSITATE=dpois(0:20, 0.1), MASA=ppois(0:20, 0.1))
p2 = data.frame(DENSITATE=dpois(0:20, 2), MASA=ppois(0:20, 2))
p3 = data.frame(DENSITATE=dpois(0:20, 5), MASA=ppois(0:20, 5))
p4 = data.frame(DENSITATE=dpois(0:20, 500), MASA=ppois(0:20, 500))
p5 = data.frame(DENSITATE=dpois(0:20, 0.01), MASA=ppois(0:20, 0.01))

plot(p1$DENSITATE, type="o",col="red", main="Poisson Ddensitate") 
lines(p2$DENSITATE,Type="o",col="green")
lines(p3$DENSITATE,Type="o",col="blue")
lines(p4$DENSITATE,Type="o",col="yellow")
lines(p5$DENSITATE,Type="o",col="coral")
legend("topright",c("l=0.1","l=2","l=5","l=500", "l=0.01"), col=c("red","green","blue","yellow","coral"),pch=15)

plot(p1$MASA,type="o",col="red", main="Poisson Masa") 
lines(p2$MASA,type="o",col="green")
lines(p3$MASA,type="o",col="blue")
lines(p4$MASA,type="o",col="yellow")
lines(p5$MASA,type="o",col="coral")
legend("bottomright",c("l=0.1","l=2","l=5","l=500", "l=0.01"), col=c("red","green","blue","yellow","coral"),pch=15)

##graficele binomial
b1 = data.frame(DENSITATE=dbinom(0:20, 20, 0.05), MASA=pbinom(0:20, 20, 0.05))
b2 = data.frame(DENSITATE=dbinom(0:20, 20, 0.1), MASA=pbinom(0:20, 20, 0.1))
b3 = data.frame(DENSITATE=dbinom(0:20, 20, 0.25), MASA=pbinom(0:20, 20, 0.25))
b4 = data.frame(DENSITATE=dbinom(0:20, 20, 0.5), MASA=pbinom(0:20, 20, 0.5))
b5 = data.frame(DENSITATE=dbinom(0:20, 20, 0.75), MASA=pbinom(0:20, 20, 0.75))

plot(b1$DENSITATE, type="o",col="red", main="Binomial Ddensitate")
lines(b2$DENSITATE,Type="o",col="green")
lines(b3$DENSITATE,Type="o",col="blue")
lines(b4$DENSITATE,Type="o",col="yellow")
lines(b5$DENSITATE,Type="o",col="coral")
legend("topright",c("p=0.05","p=0.1","p=0.25","p=0.5", "p=0.75"), col=c("red","green","blue","yellow","coral"),pch=15)

plot(b1$MASA,type="o",col="red", main="Binomial Masa")
lines(b2$MASA,type="o",col="green")
lines(b3$MASA,type="o",col="blue")
lines(b4$MASA,type="o",col="yellow")
lines(b5$MASA,type="o",col="coral")
legend("bottomright",c("p=0.05","p=0.1","p=0.25","p=0.5", "p=0.75"), col=c("red","green","blue","yellow","coral"),pch=15)

##graficele exponential
e1 = data.frame(DENSITATE=dexp(0:20, 0.25), MASA=pexp(0:20, 0.25))
e2 = data.frame(DENSITATE=dexp(0:20, 0.75), MASA=pexp(0:20, 0.75))
e3 = data.frame(DENSITATE=dexp(0:20, 1), MASA=pexp(0:20, 1))
e4 = data.frame(DENSITATE=dexp(0:20, 5), MASA=pexp(0:20, 5))
e5 = data.frame(DENSITATE=dexp(0:20, 20), MASA=pexp(0:20, 20))

plot(e1$DENSITATE, type="o",col="red", main="Exponential Ddensitate")
lines(e2$DENSITATE,Type="o",col="green")
lines(e3$DENSITATE,Type="o",col="blue")
lines(e4$DENSITATE,Type="o",col="yellow")
lines(e5$DENSITATE,Type="o",col="coral")
legend("topright",c("r=0.25","r=0.75","r=1","r=5", "r=20"), col=c("red","green","blue","yellow","coral"),pch=15)

plot(e1$MASA,type="o",col="red", main="Exponential Masa") 
lines(e2$MASA,type="o",col="green")
lines(e3$MASA,type="o",col="blue")
lines(e4$MASA,type="o",col="yellow")
lines(e5$MASA,type="o",col="coral")
legend("bottomright",c("r=0.25","r=0.75","r=1","r=5", "r=20"), col=c("red","green","blue","yellow","coral"),pch=15)

##graficele normal
n1 = data.frame(DENSITATE=dnorm(0:20, 0, 1), MASA=pnorm(0:20, 0, 1))
n2 = data.frame(DENSITATE=dnorm(0:20, 0, 5), MASA=pnorm(0:20, 0, 5))
n3 = data.frame(DENSITATE=dnorm(0:20, 5, 1), MASA=pnorm(0:20, 5, 1))
n4 = data.frame(DENSITATE=dnorm(0:20, 10, 1), MASA=pnorm(0:20, 10, 1))
n5 = data.frame(DENSITATE=dnorm(0:20, 2, 10), MASA=pnorm(0:20, 2, 10))

plot(n1$DENSITATE, type="o",col="red", main="Normal Ddensitate")
lines(n2$DENSITATE,Type="o",col="green")
lines(n3$DENSITATE,Type="o",col="blue")
lines(n4$DENSITATE,Type="o",col="yellow")
lines(n5$DENSITATE,Type="o",col="coral")
legend("topright",c("m=0, sd=1","m=0, sd=5","m=5, sd=1","m=10,sd=1", "m=2,sd=10"), col=c("red","green","blue","yellow","coral"),pch=15)

plot(n1$MASA,type="o",col="red", main="Normal Masa") 
lines(n2$MASA,type="o",col="green")
lines(n3$MASA,type="o",col="blue")
lines(n4$MASA,type="o",col="yellow")
lines(n5$MASA,type="o",col="coral")
legend("bottomright",c("m=0, sd=1","m=0, sd=5","m=5, sd=1","m=10,sd=1", "m=2,sd=10"), col=c("red","green","blue","yellow","coral"),pch=15)

#Ex 4

##generare seturi de date dist Poisson si Binomial
p10 = data.frame(DENSITATE=dpois(0:10, 1), MASA=ppois(0:10,1))
p25 = data.frame(DENSITATE=dpois(0:25, 1), MASA=ppois(0:25,1))
p50 = data.frame(DENSITATE=dpois(0:50, 1), MASA=ppois(0:50,1))
p100 = data.frame(DENSITATE=dpois(0:100, 1), MASA=ppois(0:100,1))
b10 = data.frame(DENSITATE=dbinom(0:10, 10, 0.1), MASA=pbinom(0:10, 10, 0.1))
b25 = data.frame(DENSITATE=dbinom(0:25, 25, 0.04), MASA=pbinom(0:25, 25, 0.04))
b50 = data.frame(DENSITATE=dbinom(0:50, 50, 0.02), MASA=pbinom(0:50, 50, 0.02))
b100 = data.frame(DENSITATE=dbinom(0:100, 100, 0.01), MASA=pbinom(0:100, 100, 0.01))

##Poisson 1 si Biniomial 10
plot(p10$DENSITATE,type="o",col="red", main="Poisson 1 si Biniomial 10 DENSITATE") 
lines(b10$DENSITATE,type="o",col="green")
legend("topright",c("poisson","binomial"), col=c("red","green"),pch=15)
plot(p10$MASA,type="o",col="red", main="Poisson 1 si Biniomial 10 MASA") 
lines(b10$MASA,type="o",col="green")
legend("bottomright",c("poisson","binomial"), col=c("red","green"),pch=15)

##Poisson 1 si Biniomial 25
plot(p25$DENSITATE,type="o",col="red", main="Poisson 1 si Biniomial 25 DENSITATE") 
lines(b25$DENSITATE,type="o",col="green")
legend("topright",c("poisson","binomial"), col=c("red","green"),pch=15)
plot(p25$MASA,type="o",col="red", main="Poisson 1 si Biniomial 25 MASA") 
lines(b25$MASA,type="o",col="green")
legend("bottomright",c("poisson","binomial"), col=c("red","green"),pch=15)

##Poisson 1 si Biniomial 50
plot(p50$DENSITATE,type="o",col="red", main="Poisson 1 si Biniomial 50 DENSITATE") 
lines(b50$DENSITATE,type="o",col="green")
legend("topright",c("poisson","binomial"), col=c("red","green"),pch=15)
plot(p50$MASA,type="o",col="red", main="Poisson 1 si Biniomial 50 MASA") 
lines(b50$MASA,type="o",col="green")
legend("bottomright",c("poisson","binomial"), col=c("red","green"),pch=15)

##Poisson 1 si Biniomial 100
plot(p100$DENSITATE,type="o",col="red", main="Poisson 1 si Biniomial 100 DENSITATE") 
lines(b100$DENSITATE,type="o",col="green")
legend("topright",c("poisson","binomial"), col=c("red","green"),pch=15)
plot(p100$MASA,type="o",col="red", main="Poisson 1 si Biniomial 100 MASA") 
lines(b100$MASA,type="o",col="green")
legend("bottomright",c("poisson","binomial"), col=c("red","green"),pch=15)

