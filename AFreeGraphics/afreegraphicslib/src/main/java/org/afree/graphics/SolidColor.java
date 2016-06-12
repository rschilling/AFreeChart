/*   1:    */ package org.afree.graphics;
/*   2:    */ 
/*   3:    */ import android.graphics.Color;
/*   4:    */ 
/*   5:    */ public class SolidColor
/*   6:    */   implements PaintType
/*   7:    */ {
/*   8:    */   private int mColor;
/*   9:    */   
/*  10:    */   public SolidColor()
/*  11:    */   {
/*  12: 31 */     this.mColor = 0;
/*  13:    */   }
/*  14:    */   
/*  15:    */   public SolidColor(int color)
/*  16:    */   {
/*  17: 39 */     this.mColor = color;
/*  18:    */   }
/*  19:    */   
/*  20:    */   public SolidColor(SolidColor solidColor)
/*  21:    */   {
/*  22: 47 */     this.mColor = solidColor.mColor;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public int getColor()
/*  26:    */   {
/*  27: 55 */     return this.mColor;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public int getAlpha()
/*  31:    */   {
/*  32: 62 */     return Color.alpha(this.mColor);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void setAlpha(int alpha)
/*  36:    */   {
/*  37: 69 */     this.mColor = Color.argb(
/*  38: 70 */       alpha, 
/*  39: 71 */       Color.red(this.mColor), 
/*  40: 72 */       Color.green(this.mColor), 
/*  41: 73 */       Color.blue(this.mColor));
/*  42:    */   }
/*  43:    */   
/*  44:    */   public SolidColor getDarkerSides()
/*  45:    */   {
/*  46: 80 */     int c = Color.argb(
/*  47: 81 */       Color.alpha(this.mColor), 
/*  48: 82 */       (int)(Color.red(this.mColor) * 0.8D), 
/*  49: 83 */       (int)(Color.green(this.mColor) * 0.8D), 
/*  50: 84 */       (int)(Color.blue(this.mColor) * 0.8D));
/*  51: 85 */     return new SolidColor(c);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean equals(Object object)
/*  55:    */   {
/*  56: 93 */     if (!(object instanceof SolidColor)) {
/*  57: 94 */       return false;
/*  58:    */     }
/*  59: 97 */     SolidColor gradientColor = (SolidColor)object;
/*  60: 99 */     if (gradientColor.getColor() == this.mColor) {
/*  61:100 */       return true;
/*  62:    */     }
/*  63:103 */     return false;
/*  64:    */   }
/*  65:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.SolidColor
 * JD-Core Version:    0.7.0.1
 */