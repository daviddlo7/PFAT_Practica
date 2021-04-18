pf2021 MapNaturals2Rationals(n)

decl
int i,j,k;
rational d;
bool tocaNum, nuevo;

begin
if (n>0)
then
  d:=0/1;
  print("Racional 1= " + q2str(d) + "~ " + q2str.decimal(d,3) + ";");
end;

if (n>1)
then
  d:=1/1;
  print("Racional 2= " + q2str(d) + "~ " + q2str.decimal(d,3) + ";");
end;

if (n>2)
then
  i:=2;
  k:=2;
  tocaNum:=true;
  nuevo:=false;
  j:=1;

  repeat
    if tocaNum
    then
      d:=j/k;
      if (denominador(d)=k)
      then nuevo:= true;
      end;
    else
      d:=k/j;
      if (denominador(d)=j)
      then nuevo:= true;
      end;
    end;
    if nuevo
    then
      i:= i+1;
      print("Racional " + int2str(i) + "= " + q2str(d) +
            "~ " + q2str.decimal(d,3) + ";");
    end;
    nuevo:= false;
    j:=j+1;
    if (j=k)
    then
      j:=1;
      tocaNum:= not tocaNum;
      if tocaNum
      then k:=k+1;
      end;
    end;  
  until n;
end;
end;
