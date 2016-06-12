/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Typeface;
/*   4:    */ 
/*   5:    */ public class Font
/*   6:    */ {
/*   7:    */   Typeface typeFace;
/*   8:    */   int size;
/*   9:    */   
/*  10:    */   public Font(String name, int style, int i)
/*  11:    */   {
/*  12: 36 */     this.typeFace = Typeface.create(name, style);
/*  13: 37 */     this.size = i;
/*  14:    */   }
/*  15:    */   
/*  16:    */   public Font(Typeface typeface, int style, int i)
/*  17:    */   {
/*  18: 48 */     this.typeFace = Typeface.create(typeface, style);
/*  19: 49 */     this.size = i;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public Font()
/*  23:    */   {
/*  24: 56 */     this.typeFace = Typeface.create(Typeface.DEFAULT, 0);
/*  25: 57 */     this.size = 8;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public int getSize()
/*  29:    */   {
/*  30: 65 */     return this.size;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public int getStyle()
/*  34:    */   {
/*  35: 74 */     return this.typeFace.getStyle();
/*  36:    */   }
/*  37:    */   
/*  38:    */   public Typeface getTypeFace()
/*  39:    */   {
/*  40: 82 */     return this.typeFace;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public boolean equals(Object o)
/*  44:    */   {
/*  45: 91 */     if (o == this) {
/*  46: 92 */       return true;
/*  47:    */     }
/*  48: 94 */     if ((o instanceof Font))
/*  49:    */     {
/*  50: 95 */       Font font = (Font)o;
/*  51: 96 */       if ((this.size != font.size) || (getStyle() != font.getStyle())) {
/*  52: 97 */         return false;
/*  53:    */       }
/*  54: 99 */       return true;
/*  55:    */     }
/*  56:101 */     return false;
/*  57:    */   }
/*  58:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.Font
 * JD-Core Version:    0.7.0.1
 */