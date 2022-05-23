	private No remover(int x, No i) throws Exception {
		if (i == null) throw new Exception("Erro ao remover!");
        else if (x < i.elemento) i.esq = remover(x, i.esq);
        else if (x > i.elemento) i.dir = remover(x, i.dir);
        else if (i.dir == null) i = i.esq;
        else if (i.esq == null) i = i.dir;
        else i.esq = maiorEsq(i, i.esq);
		return i;
	}
