// #include <bits/stdc++.h>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <queue>
#include <deque>
#include <bitset>
#include <iterator>
#include <list>
#include <stack>
#include <map>
#include <set>
#include <functional>
#include <numeric>
#include <utility>
#include <limits>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
using namespace std;
//vrum vrum
#define fast_io ios_base::sync_with_stdio(0); cin.tie(0);
//constants
#define PI 3.1415926535897932384626433832795
#define MOD 1000000007
#define exp 1e9
#define imax INT_MAX
#define imin INT_MIN
//functions
#define sqr(a) (a)*(a)
#define sz(x) ((int)x.size())
#define watch(x) cout << (#x) << " is " << (x) << endl
//utils
#define umap unordered_map
#define uset unordered_set
#define pb push_back
#define mk make_pair
#define fi first
#define sc second
//types
typedef long long ll;
typedef long double ld;
typedef vector<int> vi;
typedef pair<int,int> pii;
bool prime(ll a) { if (a==1) return 0; for (int i=2;i*i<=a;++i) if (a%i==0) return 0; return 1; }
// #define endl "\n"

typedef struct Node{
	char data;
	Node *left, *right;
}Node;
typedef Node* ref_node;

void preOrder(Node *root){
	if(root == NULL) return;
	cout << root->data << " ";
	preOrder(root->left);
	preOrder(root->right);
}
void inOrder(Node *root){
	if(root == NULL) return;
	inOrder(root->left);
	cout << root->data << " ";
	inOrder(root->right);
}
void postOrder(Node *root){
	if(root == NULL) return;
	postOrder(root->left);
	postOrder(root->right);
	cout << root->data << " ";
}

/*
 * creates a new node in memory
 * */
ref_node getNewNode(char data){
	ref_node newNode = new Node();//(ref_node) malloc(sizeof(Node));//new Node();
	newNode->data = data;
	return newNode;
}

ref_node insert(Node* root, char data){
	//if it's null, create a new node with the current data
	if(root == NULL){
		root = getNewNode(data);
		return root;
	}
	// if data is less, go to the left
	else if(data <= root->data)
		root->left = insert(root->left, data);
	//if data is more go to the right
	else root->right = insert(root->right, data);
	return root;
}

bool search(ref_node root, char data){
	if(root == NULL) return false;
	else if(root->data == data) return true;
	else if(data <= root->data) return search(root->left, data);
	return search(root->right, data);
}

void solve(){
	ref_node root = NULL;
	int n; cin >> n;
	vi v;
	while(n--){
		int k; cin >> k;
		v.pb(k);
	}
	for(auto &x : v)
		root = insert(root, x);
	cout << "Pre.: "; 
	preOrder(root);
	cout << "\n";
	cout << "In..: "; 
	inOrder(root);
	cout << "\n";
	cout << "Post: "; 
	postOrder(root);
	cout << "\n";
	cout << "\n";
}

// cout << "Case #" << t << ": ";
int main(){
	// fast_io;
	ref_node root = NULL;
	string s;	
	while(getline(cin, s)){
		// cout << s << "\n";
		if(s.length() == 3){
			if(s[0] == 'I')
				root = insert(root, s[2]);
			else{
				if(search(root, s[2]))
					cout << s[2] << " existe\n";
				else
					cout << s[2] << " nao existe\n";
			}
		}else{
			if(s == "INFIXA"){
				inOrder(root);
				cout << "\n";
			}
			else if (s == "PREFIXA"){
				preOrder(root);
				cout << "\n";
			}
			else{
				postOrder(root);
				cout << "\n";
			}
		}
	}
	return (0);
}
