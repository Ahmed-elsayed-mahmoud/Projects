#ifndef PASS1LISTFILE_H
#define PASS1LISTFILE_H
#include <bits/stdc++.h>
#include <iostream>
#include <fstream>
#include"Instruction.h"
using namespace std;
class Pass1ListFile
{
    public:
        Pass1ListFile(std::vector < Instruction > x ,std::map<string,string>y );
        virtual ~Pass1ListFile();
        void createListFile(vector<Instruction> instructionList);
        void createSymbolTable(std::map<string,string>y);
    protected:
    private:
};

#endif // PASS1LISTFILE_H
