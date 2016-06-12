package org.afree.graphics;

public abstract interface PaintType
{
  public abstract int getAlpha();
  
  public abstract void setAlpha(int paramInt);
  
  public abstract PaintType getDarkerSides();
  
  public abstract boolean equals(Object paramObject);
}


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.PaintType
 * JD-Core Version:    0.7.0.1
 */