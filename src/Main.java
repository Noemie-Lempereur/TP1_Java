public class Main {
    public static void main(String[] args) {
        String signe = new String("*");
        boolean horizontal = true;

        int indiceO = indice(args,"-o");
        if(indiceO != -1){
            args[indiceO] = "-1";
            if(args[indiceO+1].equals("v")){
                horizontal=false;
            }
            args[indiceO+1] = "-1";
        }

        int indice = indice(args,"-s");
        if(indice != -1){
            args[indice] = "-1";
            signe = args[indice+1];
            args[indice+1] = "-1";
        }

        int indice2 = indice(args,"-v");
        if(indice2 != -1){
            int longueur=0;
            if(indice2>indice && indice2>indiceO) {
                longueur= args.length;
            }
            if(indice2>indice && indice2<indiceO) {
                longueur = indiceO;
            }
            if(indice2<indice && indice2>indiceO) {
                longueur= indice;
            }
            if(indice2<indice && indice2<indiceO && indice<indiceO) {
                longueur= indice;
            }
            if(indice2<indice && indice2<indiceO && indice>indiceO) {
                longueur= indiceO;
            }
            String [] args2 = new String[longueur-indice2-1];
            int i2=0;
            for(int i = indice2 + 1 ; i< longueur;i++){
                args2[i2]=args[i];
                i2++;
            }
            int [] result1 = new int[args2.length];
            int [] result2 = new int[args2.length];
            result1 = getIntegerValues(args2);
            printHorizontalHistogram(result1, signe, horizontal);
        }else{
            System.out.print("Erreur pas d'argument -v");
        }


        //result2 = getOccurrences(result1);
        /*for (int value:result2) {
            System.out.print(value+" ");
        }*/
    }

    public static int[] getIntegerValues(String[] args){
        //convertit le tableau de chaînes de caractères args en tableau d’entiers.
        int [] result = new int[args.length];
        int increment = 0;
        for (String value: args){
            int res = Integer.parseInt(value);
            result[increment] = res;
            increment++;
        }
        return result;
    }

    public static int[] getOccurrences(int[] values){
        // Renvoie sous la forme d’un tableau d’entiers, le nombre d’occurrences de chaque chiffre présent dans le tableau values.
        // Exemple : si values vaut [1, 2, 2, 4], alors getOccurrences(values) doit renvoyer [0, 1, 2, 0, 1]
        int [] nbOccurences = new int[10];
        for (int value:values) {
            if(value >= 0 && value <= 9) {
                nbOccurences[value]++;
            }
        }
        int decrement = 9;
        while (nbOccurences[decrement]==0){
            decrement--;
        }
        int [] result = new int[decrement+1];
        for (int i = 0; i <= decrement ; i++){
            result[i]=nbOccurences[i];
        }
        return result;
    }

    public static void printHorizontalHistogram(int[] values, String signe, boolean horizontal){
        int[] occurences = new int[10];
        occurences = getOccurrences(values);
        boolean minFind = false;
        int iterator = 0;
        if(horizontal) {
            for (int value : occurences) {
                if (minFind || value != 0) {
                    minFind = true;
                    System.out.print(iterator+" - ");
                    for (int i = 0; i < value; i++) {
                        System.out.print(signe);
                    }
                    System.out.println();
                }
                iterator++;
            }
        }else{
            int max = 0;
            boolean maxFind = false;
            int iteratorMin=0;
            int iteratorMax = occurences.length;;
            boolean locked = false;
            boolean locked2 = false;
            for (int val = 0; val<occurences.length;val++) {
                if ((minFind || occurences[val] != 0)&&!locked) {
                    minFind = true;
                    iteratorMin=iterator;
                    locked=true;
                }
                if ((maxFind || occurences[occurences.length-val-1] != 0)&&!locked) {
                    maxFind = true;
                    iteratorMax= occurences.length-val;
                    locked2=true;
                }
                max = Math.max(max, occurences[val]);
                iterator++;
            }
            for(int i = max ; i>0;i--) {
                for (int val = 0; val<occurences.length;val++) {
                    if (i <= occurences[val] && val>=iteratorMin) {
                        System.out.print(signe);
                    }
                    if (i > occurences[val] && val>=iteratorMin) {
                        for (int j = 0; j<signe.length();j++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            for (int k = iteratorMin; k<iteratorMax;k++){
                System.out.print(k);
            }
        }
    }

    public static int indice(String[] args, String option){
        int iterator = 0;
        for (String value:args) {
            if (value.equals(option)){
                return iterator;
            }
            iterator++;
        }
        return -1;
    }
}