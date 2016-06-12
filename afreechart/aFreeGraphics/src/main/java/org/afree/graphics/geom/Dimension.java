/*  1:   */ package org.afree.graphics.geom;
/*  2:   */ 
/*  3:   */ public class Dimension
/*  4:   */ {
/*  5:   */   private float mWidth;
/*  6:   */   private float mHeight;
/*  7:   */   
/*  8:   */   public Dimension(float width, float height)
/*  9:   */   {
/* 10:32 */     this.mWidth = width;
/* 11:33 */     this.mHeight = height;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public float getWidth()
/* 15:   */   {
/* 16:41 */     return this.mWidth;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public float getHeight()
/* 20:   */   {
/* 21:49 */     return this.mHeight;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setSize(Dimension dimension)
/* 25:   */   {
/* 26:57 */     this.mWidth = dimension.getWidth();
/* 27:58 */     this.mHeight = dimension.getHeight();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setSize(float width, float height)
/* 31:   */   {
/* 32:67 */     this.mWidth = width;
/* 33:68 */     this.mHeight = height;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean equals(Object o)
/* 37:   */   {
/* 38:76 */     if ((o instanceof Dimension))
/* 39:   */     {
/* 40:77 */       Dimension dimension = (Dimension)o;
/* 41:78 */       if ((this.mWidth == dimension.mWidth) && 
/* 42:79 */         (this.mHeight == dimension.mHeight)) {
/* 43:81 */         return true;
/* 44:   */       }
/* 45:   */     }
/* 46:84 */     return false;
/* 47:   */   }
/* 48:   */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.Dimension
 * JD-Core Version:    0.7.0.1
 */