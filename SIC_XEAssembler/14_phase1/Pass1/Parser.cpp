#include "Parser.h"
#include<regex>
#include<bits/stdc++.h>

using namespace std ;
Instruction inst ;
Parser::Parser()
{
    //ctor
}
void Parser::parse(string line, int format)
{inst =  Instruction();
    fileFormat = format;
    if(fileFormat == fixedFormat ) {
        linePartsQueue = queue<string>();
        lineRemQueue = queue<string>();
        validateFixed(getUpperCase(line));
    }

    inst.setCommand(line);
    check.setSymbolTable(symbolTable);
    std::string newLine;
    newLine+= line;
    newLine = removeFirstSpaces(newLine);
    if((newLine.compare("\0")&&newLine.size()==1) || newLine.size() == 0 )
    {
        inst.setError("");
        inst.setBlank();
        inst.setCommand(line);
        inst.setFormat(0);
    }
    else if( newLine[0] == '.' )
    {
        inst.setError("");
        inst.setComment(line);
        inst.setCommand(line);
    }
    else
    {
        if(endFlag == true) inst.setError("unrecognised operation");
        newLine = getUpperCase(newLine);
        string first = getFirstWord(newLine);
        int format = check.getFormat( first );
        if( format == 0 )  //1st word is not operation
        {
            if(!check.isAcceptedLabel(first))
            {
                inst.setError("unrecognised operation");
            }
            else if((symbolTable.count(first)!= 0))
            {
                inst.setError("redifined Label");
            }
            else
            {
                newLine = RemoveFirstWord(newLine);

                if(newLine.size() > 0)
                {
                    string second = getFirstWord(newLine);

                    inst.setLabel(first);
                    format = check.getFormat(second);
                    solve(format, newLine ,first, second);
                }
                else
                {
                    inst.setError("unrecognised operation");
                }
            }
        }
        else
        {
            newLine = removeFirstSpaces(newLine);
            solve(format,newLine,"",first);
        }
    }
}
void Parser::solve(int format, string line,string label, string operators)
{//<<line<<endl;
    regex resw_operand_reg = regex( "^0$|^[1-9][0-9]*$");
    regex resb_operand_reg = regex("\\d{1,4}");
    regex start_operand_reg = regex("([A-F]|\\d){0,4}");
    regex end_operand_reg = regex("([A-Z]|\\d){0,4}");
    regex byte_operand_hexa_reg = regex("((X[\']([A-F]|\\d){0,14}[\']))");
    regex byte_operand_char_reg = regex("((c|C)'((\\s){0,1}(\\w){0,1}){0,15}')");
    regex word_operand_reg = regex("(-?\\d{1,4}(,-?\\d{1,4})*)");// here
    regex address_operand_reg = regex("(([A-F]|\\d){0,14})");

    if(fileFormat == freeFormat){
        operators = getFirstWord(line);
        line = RemoveFirstWord(line);
    }
    string operands = "";
    if(line.size() > 0)
    {
      string dd = getFirstWord(line);
       int checkOperand =0, checkOperator=0;
       checkOperand= check.getFormat(dd);
       checkOperator = check.getFormat(operators);
       if(checkOperand != 0 && checkOperator !=0  && fileFormat == freeFormat){
            inst.setError("unrecognised operation");
       }else{
            operands = dd;
       }
    }
    else
    {
        operands = "0";

    }
    if(format == 1)   //directive
    {
        string op2 = "";
        if (line.size() != 0 && operators == "BYTE"){
            int i=0;
            bool found1 =false, found2 = false;
            while(!found2 && i<line.size()){
                if(line[i] == '\'' && !found1)
                    found1 = true;
                else if(line[i] == '\'' && !found1)
                    found2 = true;
                op2 += line[i];
                i++;
            }
            if (isspace(op2.at(op2.size()-1)))
            {
                op2=op2.substr(0,op2.size()-1);
            }
            operands = op2;
        }
        if(operators == "ORG" || operators == "EQU" ||operators == "BASE" || operators == "NOBASE" || operators == "LTORG"){
                inst.setFormat(0);
                inst.setError("warninig undefine directive");
//                if(label.size() > 0 && operators != "EQU"){
//                    inst.setError("Invalid label");
//                }
                inst.setOperator(operators);

        }
        else if(regex_match( operands , start_operand_reg ) && operators == "START")
        {
            startFlag = true ;
            start = label;
            inst.setFormat(1);
            inst.setOperator(operators);
            inst.setOperand(operands);
        }
        else if(
            ( ( operands == "" || regex_match( operands , end_operand_reg ) ) && operators == "END")||
            ( ( operands == "" || operands == start ) && operators == "END")
        )
        {
            endFlag = true;
            if(label != "" )inst.setError("illegal label");
            inst.setFormat(1);
            inst.setOperator(operators);
            inst.setOperand(operands);
        }
        else if ( regex_match( operands , word_operand_reg ) && operators == "WORD" ){
            inst.setOperator(operators);
            inst.setOperand(operands);
            inst.setFormat( 3 );
        }
        else if( (regex_match( operands , byte_operand_char_reg ) ||regex_match( op2 , byte_operand_char_reg ))
                   && operators == "BYTE"){
            inst.setOperator(operators);
            inst.setOperand(operands);
            inst.setFormat(operands.size() - 3);
        }
        else if(regex_match( operands , byte_operand_hexa_reg ) && operators == "BYTE"){
            inst.setOperator(operators);
            inst.setOperand(operands);
            if((operands.size() - 3) %2 ==0)
            inst.setFormat((operands.size() - 3) / 2);
            else inst.setError("inValidOperand");
        }
        else if( regex_match( operands , resw_operand_reg ) && operators == "RESW"){
            inst.setOperator(operators);
            inst.setOperand(operands);
            inst.setFormat(atoi(operands.c_str()) *3);
        }
        else if(regex_match( operands , resb_operand_reg ) && operators == "RESB"){
            inst.setOperator(operators);
            inst.setOperand(operands);
            inst.setFormat(atoi(operands.c_str()));
        }
        else
        {
            inst.setError("INVALID OPERAND");
        }

    }
    else if(format > 1)
    {
        switch(format)
        {
        case 2:
            inst.setFormat(2);
            break;
        case 3:
            inst.setFormat(3);
            break;
        case 4:
            inst.setFormat(4);
            break;
        }
        inst.setOperand(operands);
        inst.setOperator(operators);

    }
    else
    {
        inst.setError("unrecognised operation");

    }
    if(!startFlag){
            inst.setError("unrecognised operation");
    }
}
string Parser::getUpperCase( string line )
{
    for(int i=0 ; i<line.size() ; ++i)
    {
        line[i] = toupper( line[i] );
    }
    return line ;
}
string Parser::removeFirstSpaces( string line )
{
    int i = 0 ;
    if((line.size()==1&&line.compare("\0") ))
        return line;
    while((line[i]==' '|| line[i] == '\t') && i<line.size() )
    {
        ++i ;
    }
    line = line.substr(i,line.size());
    return line ;
}
string Parser::getFirstWord( string line )
{
    if(fileFormat == fixedFormat && !linePartsQueue.empty()) {
        string ff = linePartsQueue.front();
        linePartsQueue.pop();
        return ff;
    }
    int i = 0 ;
    line = removeFirstSpaces(line);
    while(i < line.size() && line[i] != ' ' && line[i]!='\t')
    {
        ++i ;
    }
//    line = removeFirstSpaces(line);
    string dd = line.substr(0,i);
    if(line.size()!=0 && line[0]=='#'){
        line[0] = ' ';
        dd= "#" + getFirstWord(line);
    }
    if(line.size()!=0 && line[0]=='@'){
        line[0] = ' ';
        dd= "@" + getFirstWord(line);
    }
    bool flag = false ;
            if(dd[dd.size()-1]==','){
               line = ","+line;
               flag = true;
            }
            if(line.size()!=0)
            line =line.substr(i,line.size()-1);
            if(line.size()!=0)
            line =removeFirstSpaces(line);
    if(line.size()!=0 && line[0] == ','){
            if(!flag)dd+=",";
            line[0]=' ';
            line = removeFirstSpaces(line);
           dd+=getFirstWord(line);
    }
    if (isspace(dd.at(dd.size()-1)))
    {
        dd=dd.substr(0,dd.size()-1);
    }
    return dd;
}

void Parser::setSymbolTable(map < string , string > m)
{
    symbolTable = m;
}
string Parser::RemoveFirstWord( string line )
{
    if(fileFormat == fixedFormat && !lineRemQueue.empty()) {
        string ff = lineRemQueue.front();
        lineRemQueue.pop();
        return ff;
    }
    int i = 0 ;
    line = removeFirstSpaces(line);
    while( i < line.size() && line[i] != ' '&&line[i]!='\t'  )
    {
        ++i ;
    }
    line = line.substr(i,line.size());
    line = removeFirstSpaces(line);
    return line ;
}
string getLabel(string s)
{
    string a;
    bool flag = false;
    int i = 0, j=0;
    regex t( "^[.]{1}(\\w|\\s|.){0,66}");
    regex first( "^(([a-z]|[A-Z])(\\s|\\w|[0-9]){0,8})|[ ]{0,9}");

    if(regex_match(s,t)){
        return "comment";
    }
    a = s.substr(0, 9);
    if(a[8] != ' ')
        return "-1";
    if (isspace(a.at(a.size()-1)))
    {
        a=a.substr(0,a.size()-1);
    }

    if(regex_match(a,first)){
        while(i < a.length() && a[i] != '\0'){
            if(a[i] == ' '){
                flag = true;
            }
            if(a[i] != ' ' && a[i] != '+' &&flag == true){
                return "-1";
            }
            i++;
        }
    }
    else
        return "-1";
    a.erase(std::remove(a.begin(),a.end(),' '),a.end());

    if(a == "")
        return "-2";
    return a;
}

string getOperator(string s)
{
    string a;
    bool flag = false;
    int i = 0;
    regex first( "([a-z]|[A-Z]|\\+)([ ]|\\w){0,5}([ ]){0,2}");
    a = s.substr(0,8);

    if (isspace(a.at(a.size()-1)))
    {
        a=a.substr(0,a.size()-1);
    }
    if(a.size()> 7&& a[6] != ' '&& a[7] != ' ')
        return "-1";
    if(regex_match(a,first)){
        while(i < a.length() && a[i] != '\0'){
            if(a[i] == ' '){
                flag = true;
            }
            if(a[i] != ' ' && a[i] != '+' &&flag == true){
                return "-1";
            }
            i++;
        }
    }
    else
        return "-1";

    return a;
}

string getOperand(string s)
{
    string a;
    bool flag = false;
    int i = 0;
    regex first( "(([a-z]|[A-Z]|\\d)(\\s+|[ ]|\\w|[0-9]){0,7}([ ]){0,10})|((#|@)([ ]|\\w|[0-9]){0,8}([ ]){0,10})|((\\*){1}[ ]{0,17})|((\\w){1,8}\\,(\\w){1}[ ]{0,15})|((c|C)'((\\s){0,1}(\\w){0,1}){1,15}')|((x|X)'([0-9]{2})')|((x|X)'(([a-f]|[A-F]){1}[0-9]{1})')|((x|X)'(([a-f]|[A-F]){2})')|((x|X)'[0-9]{1}(([a-f]|[A-F]){1})')");
    a = s.substr(0,18);
    if (isspace(a.at(a.size()-1)))
    {
        a=a.substr(0,a.size()-1);
    }
    if(regex_match(a,first)){
        while(i < a.length() && a[i] != '\0'){
            if(a[i] == ' '){
                flag = true;
            }
            if(a[i] == '\'' &&a[i-1] == 'C'){
                break;
            }
            if(a[i] != ' ' && a[i] != '+' &&flag == true){
                return "-1";
            }
            i++;
        }
        if(a[i+1] == '\'')
            return "-1";
    }
    else
        return "-1";
    return a;
}

void Parser::validateFixed(string input){
    string label = "-1", operat = "-1", operand = "-1", comment = "-1";
    string lineRem1, lineRem2, lineRem3;
    int i = 0;

    regex fourth("([ ]|\\w|[0-9]){0,31}");
    label = "-1";
    operat = "-1";
    operand = "-1";
    comment = "\nthere is no comment\n";
    label = getLabel(input);
    if(input.length()>=9){
        lineRem1 = input.substr(9,17);
        lineRemQueue.push(lineRem1);
        operat = getOperator(lineRem1);
        operat.erase(std::remove(operat.begin(), operat.end(), ' '), operat.end());
    }
    if(input.length()>=17){
        lineRem2 = input.substr(17,35);
        lineRemQueue.push(lineRem2);
        operand = getOperand(lineRem2);
        operand.erase(std::remove(operand.begin(), operand.end(), ' '), operand.end());
    }
    if(input.length()>=35){
        lineRem3 = input.substr(35,66);
        lineRemQueue.push(lineRem3);
        if(regex_match(lineRem3,fourth)){
            comment = "\nwith comment\n";
        }
        else{
            comment = "\nthere is no comment\n";
        }
    }

    if(label == "-1"){
        inst.setError("Invalid Label");
    }else if (label !="" && label != "-2"){
        linePartsQueue.push(label);
    }else if(label != "-2"){
        linePartsQueue.push("");
    }
    if(label == "comment"){
        inst.setComment(input);
    }

    if(operat == "-1"){
        inst.setError("Invalid Operator");
    }else{
        linePartsQueue.push(operat);
    }
    if(operand != "-1"){
        linePartsQueue.push(operand);
    }
}
map < string , string > Parser::getSymbolTable()
{
    return symbolTable;
}
Instruction Parser::getInstruction()
{
    return inst;
}

Parser::~Parser()
{
    //dtor
}
