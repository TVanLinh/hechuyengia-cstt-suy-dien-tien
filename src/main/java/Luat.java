public class Luat
{
   public String vp;
   public String vt;

   public Luat()
   {}
   public Luat(String vt,String vp)
   {
       this.vt=vt;
       this.vp=vp;
   }

   public String toString()
   {
       return(this.vt+"  ->  "+this.vp);
   }
}

