#include "Pass1.h"
#include "Pass1ListFile.h"
#include"Pass2.h"
using namespace std;
int main(int argc, char* const argv[])
{
    string fileName;
    if(argc>1)
        fileName = argv[1];
    int type=0;
    Pass1 pass1(fileName,type);

}
