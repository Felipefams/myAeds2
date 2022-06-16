#include <bits/stdc++.h>
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

typedef struct Node{
	int data;
	Node *left, *right;
	Node(int);
}Node;
typedef Node* ref_node;

void preOrder(Node *root){
	if(root == NULL) return;
	printf("%d ", root->data);
	preOrder(root->left);
	preOrder(root->right);
}
void inOrder(Node *root){
	if(root == NULL) return;
	inOrder(root->left);
	printf("%d ", root->data);
	inOrder(root->right);
}
void postOrder(Node *root){
	if(root == NULL) return;
	postOrder(root->left);
	postOrder(root->right);
	printf("%d ", root->data);
}
/*
 * creates a new node in memory
 * */
ref_node getNewNode(int data){
	ref_node newNode = (ref_node) malloc(sizeof(Node));//new Node();
	newNode->data = data;
	return newNode;
}

ref_node insert(Node* root, int data){
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
	cout << endl;
	cout << "In..: "; 
	inOrder(root);
	cout << endl;
	cout << "Post: "; 
	postOrder(root);
	cout << endl;
	cout << endl;
}

// cout << "Case #" << t << ": ";
int main(){
//	fast_io;
	int t = 0;
	cin >> t;
	for(int i = 1; i < t+1; ++i){
		printf("Case %d:\n", i);
		solve();
	}
	return (0);
}
