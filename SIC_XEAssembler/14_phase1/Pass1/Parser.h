#include<regex>
#include <bits/stdc++.h>
#include <vector>
#include <queue>
#include <iostream>
#include <string.h>
#ifndef PARSER_H
#define PARSER_H
#include <bits/c++io.h>
#include "Instruction.h"

#include "Check.h"

using namespace std ;
class Parser
{
    public:
        Parser();
        virtual ~Parser();
        string removeFirstSpaces( string line );
        string getUpperCase( string line );
        string getFirstWord( string line );
        string RemoveFirstWord( string line );
        map < string , string > symbolTable;
        void solve(int format, string newLine,string label,string oper);
        void setSymbolTable(map < string , string >);
        map < string , string > getSymbolTable();
        Instruction getInstruction();
        bool endFlag=false;
        bool startFlag = false;
        void parse(string line, int format);
        void validateFixed(string line);
        queue<string> linePartsQueue;
        queue<string> lineRemQueue;
        int fileFormat , fixedFormat=0, freeFormat=1;
        string start = "";
        Check check;

    protected:
    private:
};

#endif // PARSER_H
