package es.health.exercise;

public class Swimming extends Exercise {

		private int numberLengths;
		private TypeSwimming typeSwimming;
		
		public enum TypeSwimming{
			POOL, SEA
		}
		
		public int getNumberLengths() {
			return numberLengths;
		}

		public void setNumberLengths(int numberLengths) {
			this.numberLengths = numberLengths;
		}

		public TypeSwimming getTypeSwimming() {
			return typeSwimming;
		}

		public void setTypeSwimming(TypeSwimming typeSwimming) {
			this.typeSwimming = typeSwimming;
		}
}
