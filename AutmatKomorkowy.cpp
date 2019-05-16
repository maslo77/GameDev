#include <iostream>
#include <bitset>
#include <string>
using namespace std;

const int ArraySize = 200;
const int NumGenerations = 10
        ;
const string InitialState = "111110101100011010001000";
int main()
{
    bitset<ArraySize +2> array(InitialState);
    for (int j = 0;j < NumGenerations;++j) {
        bitset<ArraySize +2> tmpArray(array);
        for (int i =ArraySize;i > 0; --i){
            if(tmpArray[i])
            {
             cout << "#";

            }else {
                cout << "_";
            }
            int val = (int)tmpArray[i-1] << 2 | (int)tmpArray[i] << 1 | (int)tmpArray[i+1];
            array[i] = (val == 3 || val == 5 || val == 6);
        }
        cout<<endl;
    }
    return 0;
}
