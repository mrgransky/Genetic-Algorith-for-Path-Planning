clc
close all;
clear all;
%%
delta = .01;
x = 0:delta:127;
xInit = 55-rand(1,1);
xFormula = 9;

y = 3+sin(.1*x.^1.2) + 1.4*cos(1+.07*x);
[yMax, idxMax] = max(y);

xInp = 62.9;
idx2Opt = xInp/delta;
randGen = ceil(100*rand(1,1));

xGopt = idxMax-floor(.04*idxMax):randGen:idxMax+floor(.04*idxMax);
yGopt = 3+sin(.1*x(xGopt).^1.2) + 1.4*cos(1+.07*x(xGopt));

x2ndOpt = idx2Opt-floor(.04*idx2Opt):ceil(5*randGen):idx2Opt+floor(.04*idx2Opt);
y2ndOpt = 3+sin(.1*x(x2ndOpt).^1.2) + 1.4*cos(1+.07*x(x2ndOpt));

yInit = 3+sin(.1*xInit.^1.2) + 1.4*cos(1+.07*xInit);
yFormula = 3+sin(.1*xFormula.^1.2) + 1.4*cos(1+.07*xFormula);
txtFormula = ' \leftarrow f(x) = sin( 0.07x^{1.2} ) + 0.4cos( 1 + 0.4x )  + 3';

%%
figure;
set(gcf,'color','white');
plot(x,y,'b');
hold on;
plot(x(idxMax),yMax,'*r');
txtGoal = ' GOAL \rightarrow ';
text(x(idxMax),yMax,txtGoal,'HorizontalAlignment','right','VerticalAlignment','middle','color','r');
hold on;
plot(xInit,yInit,'*g');
txtInit = '  \leftarrow START';
text(xInit,yInit,txtInit,'HorizontalAlignment','left','VerticalAlignment','baseline','color','g');
text(xFormula,yFormula,txtFormula,'Rotation',15,'FontAngle','italic','HorizontalAlignment','left','VerticalAlignment','baseline','color','k');
xlabel('x [m]');
ylabel('Fitness Function');
axis ([0 127 0.6 5.5])

%%
figure;
set(gcf,'color','white');
subplot(121)
plot(x,y,'b');
hold on;

xTemp = floor(size(x,2)*rand(10,1));
yTemp = zeros(size(xTemp,1),1);
for i = 1:size(xTemp,1)
    yTemp(i) = 3+sin(.1*x(xTemp(i)).^1.2) + 1.4*cos(1+.07*x(xTemp(i)));
    plot(x(xTemp(i)),yTemp(i),'om');
    hold on;
end
xlabel('x [m]');
ylabel('Fitness Function');
axis ([0 127 0.6 5.5])
title('N_{pop} = 10');

subplot(122)
plot(x,y,'b');
hold on;
xTemp = ceil(size(x,2)*rand(50,1));
yTemp = zeros(size(xTemp,1),1);
for i = 1:size(xTemp,1)
    yTemp(i) = 3+sin(.1*x(xTemp(i)).^1.2) + 1.4*cos(1+.07*x(xTemp(i)));
    plot(x(xTemp(i)),yTemp(i),'om');
    hold on;
end
xlabel('x [m]');
ylabel('Fitness Function');
axis ([0 127 0.6 5.5]);
title('N_{pop} = 50');
%%
figure;
set(gcf,'color','white');
plot(x,y);
axis ([0 127 0.6 5.5]);
hold on;

for i = 1:size(xGopt,2)
plot(x(xGopt(i)),yGopt(i),'xr','LineWidth',4);
hold on;
end
hold on;

for i = 1:size(x2ndOpt,2)
plot(x(x2ndOpt(i)),y2ndOpt(i),'xm','LineWidth',4);
hold on;
end

hold on;
hold on;
plot(x(idxMax),yMax,'*r');
txtGoal = ' \leftarrow GOAL  ';
text(x(idxMax),yMax,txtGoal,'HorizontalAlignment','left','VerticalAlignment','middle','color','r');

xlabel('x [m]');
ylabel('Fitness Function');

