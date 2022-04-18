#include <bits/stdc++.h>
#define ll long long
using namespace std;

const ll *fib_cache;

ll fib(int n){
	if( n <= 1 ){
		return n;
	}
	if(fib_cache[n] != NULL){
		return fib_cache[n];
	}
	ll fibAns = fib(n-2) + fib(n-1);
	return fibAns;
}

int main(void){
	int n = 0; 
	cin >> n;
	fib_cache = new ll[n+1];
	cout << fib(n) << '\n';
	return(0);
}
