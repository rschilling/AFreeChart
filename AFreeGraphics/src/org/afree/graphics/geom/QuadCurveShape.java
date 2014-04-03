/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Paint;
/*   5:    */ import android.graphics.Paint.Style;
/*   6:    */ import android.graphics.Path;
/*   7:    */ import android.graphics.PointF;
/*   8:    */ import android.graphics.Rect;
/*   9:    */ import android.graphics.RectF;
/*  10:    */ import android.graphics.Region;
/*  11:    */ import android.graphics.Region.Op;
/*  12:    */ 
/*  13:    */ public class QuadCurveShape
/*  14:    */   implements Shape
/*  15:    */ {
/*  16:    */   private float mX1;
/*  17:    */   private float mY1;
/*  18:    */   private float mX2;
/*  19:    */   private float mY2;
/*  20:    */   private float mCtrlx;
/*  21:    */   private float mCtrly;
/*  22:    */   private Path mPath;
/*  23:    */   private Region mRegion;
/*  24: 37 */   private RectF rectBuffer = new RectF();
/*  25:    */   
/*  26:    */   public QuadCurveShape()
/*  27:    */   {
/*  28: 43 */     this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public QuadCurveShape(float x1, float y1, float ctrlx, float ctrly, float x2, float y2)
/*  32:    */   {
/*  33: 57 */     this.mX1 = x1;
/*  34: 58 */     this.mY1 = y1;
/*  35: 59 */     this.mCtrlx = ctrlx;
/*  36: 60 */     this.mCtrly = ctrly;
/*  37: 61 */     this.mX2 = x2;
/*  38: 62 */     this.mY2 = y2;
/*  39: 63 */     this.mPath = new Path();
/*  40:    */     
/*  41: 65 */     update();
/*  42:    */   }
/*  43:    */   
/*  44:    */   public QuadCurveShape(QuadCurveShape curve)
/*  45:    */   {
/*  46: 73 */     this.mX1 = curve.mX1;
/*  47: 74 */     this.mY1 = curve.mY1;
/*  48: 75 */     this.mCtrlx = curve.mCtrlx;
/*  49: 76 */     this.mCtrly = curve.mCtrly;
/*  50: 77 */     this.mX2 = curve.mX2;
/*  51: 78 */     this.mY2 = curve.mY2;
/*  52: 79 */     this.mPath = curve.mPath;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void clip(Canvas canvas) {}
/*  56:    */   
/*  57:    */   public boolean contains(float x, float y)
/*  58:    */   {
/*  59: 94 */     return false;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean contains(float x, float y, float width, float height)
/*  63:    */   {
/*  64:102 */     return false;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public boolean contains(PointF point)
/*  68:    */   {
/*  69:110 */     return false;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean contains(RectShape rect)
/*  73:    */   {
/*  74:118 */     return false;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void draw(Canvas canvas, Paint paint)
/*  78:    */   {
/*  79:125 */     paint.setStyle(Paint.Style.STROKE);
/*  80:126 */     canvas.drawPath(this.mPath, paint);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void fill(Canvas canvas, Paint paint)
/*  84:    */   {
/*  85:133 */     paint.setStyle(Paint.Style.FILL);
/*  86:134 */     canvas.drawPath(this.mPath, paint);
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  90:    */   {
/*  91:141 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  92:142 */     canvas.drawPath(this.mPath, paint);
/*  93:    */   }
/*  94:    */   
/*  95:    */   @Deprecated
/*  96:    */   public RectShape getBounds()
/*  97:    */   {
/*  98:150 */     return new RectShape(this.rectBuffer);
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void getBounds(RectShape rect)
/* 102:    */   {
/* 103:157 */     rect.setRectF(this.rectBuffer);
/* 104:    */   }
/* 105:    */   
/* 106:    */   public Path getPath()
/* 107:    */   {
/* 108:164 */     return this.mPath;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean intersects(float x, float y, float width, float height)
/* 112:    */   {
/* 113:173 */     Region region = new Region(this.mRegion);
/* 114:    */     float tmpw;
/* 115:    */     float tmpx;
/* 116:    */
/* 117:174 */     if (x < width + x)
/* 118:    */     {
/* 119:175 */       tmpx = x;
/* 120:176 */       tmpw = width;
/* 121:    */     }
/* 122:    */     else
/* 123:    */     {
/* 124:179 */       tmpx = width + x;
/* 125:180 */       tmpw = Math.abs(width);
/* 126:    */     }
/* 127:    */     float tmph;
/* 128:    */     float tmpy;
/* 129:    */    
/* 130:182 */     if (y < height + y)
/* 131:    */     {
/* 132:183 */       tmpy = y;
/* 133:184 */       tmph = height;
/* 134:    */     }
/* 135:    */     else
/* 136:    */     {
/* 137:187 */       tmpy = height + y;
/* 138:188 */       tmph = Math.abs(height);
/* 139:    */     }
/* 140:190 */     region.getBounds();
/* 141:191 */     return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 142:    */   }
/* 143:    */   
/* 144:    */   public boolean intersects(Rect rect)
/* 145:    */   {
/* 146:198 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public boolean intersects(RectShape rect)
/* 150:    */   {
/* 151:205 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void translate(float x, float y)
/* 155:    */   {
/* 156:212 */     this.mX1 += x;
/* 157:213 */     this.mY1 += y;
/* 158:214 */     this.mCtrlx += x;
/* 159:215 */     this.mCtrly += y;
/* 160:216 */     this.mX2 += x;
/* 161:217 */     this.mY2 += y;
/* 162:    */     
/* 163:219 */     update();
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setCurve(float x1, float y1, float ctrlx, float ctrly, float x2, float y2)
/* 167:    */   {
/* 168:233 */     this.mX1 = x1;
/* 169:234 */     this.mY1 = y1;
/* 170:235 */     this.mCtrlx = ctrlx;
/* 171:236 */     this.mCtrly = ctrly;
/* 172:237 */     this.mX2 = x2;
/* 173:238 */     this.mY2 = y2;
/* 174:    */     
/* 175:240 */     update();
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void setCurve(double x1, double y1, double ctrlx, double ctrly, double x2, double y2)
/* 179:    */   {
/* 180:254 */     this.mX1 = ((float)x1);
/* 181:255 */     this.mY1 = ((float)y1);
/* 182:256 */     this.mCtrlx = ((float)ctrlx);
/* 183:257 */     this.mCtrly = ((float)ctrly);
/* 184:258 */     this.mX2 = ((float)x2);
/* 185:259 */     this.mY2 = ((float)y2);
/* 186:    */     
/* 187:261 */     update();
/* 188:    */   }
/* 189:    */   
/* 190:    */   private void update()
/* 191:    */   {
/* 192:268 */     this.mPath.reset();
/* 193:269 */     this.mPath.moveTo(this.mX1, this.mY1);
/* 194:270 */     this.mPath.quadTo(this.mCtrlx, this.mCtrly, this.mX2, this.mY2);
/* 195:    */     
/* 196:272 */     this.mPath.computeBounds(this.rectBuffer, false);
/* 197:273 */     this.mRegion = new Region((int)this.rectBuffer.left, (int)this.rectBuffer.top, (int)this.rectBuffer.right, (int)this.rectBuffer.bottom);
/* 198:    */     
/* 199:275 */     this.mPath.lineTo(this.mX2 + 1.0F, this.mY2);
/* 200:276 */     this.mPath.quadTo(this.mCtrlx + 1.0F, this.mCtrly, this.mX1 + 1.0F, this.mY1);
/* 201:277 */     this.mPath.close();
/* 202:    */     
/* 203:279 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 204:    */   }
/* 205:    */   
/* 206:    */   public boolean equals(Object o)
/* 207:    */   {
/* 208:287 */     if ((o instanceof QuadCurveShape))
/* 209:    */     {
/* 210:288 */       QuadCurveShape quadCurveShape = (QuadCurveShape)o;
/* 211:289 */       if ((this.mX1 == quadCurveShape.mX1) && 
/* 212:290 */         (this.mY1 == quadCurveShape.mY1) && 
/* 213:291 */         (this.mX2 == quadCurveShape.mX2) && 
/* 214:292 */         (this.mY2 == quadCurveShape.mY2) && 
/* 215:293 */         (this.mCtrlx == quadCurveShape.mCtrlx) && 
/* 216:294 */         (this.mCtrly == quadCurveShape.mCtrly)) {
/* 217:296 */         return true;
/* 218:    */       }
/* 219:    */     }
/* 220:299 */     return false;
/* 221:    */   }
/* 222:    */   
/* 223:    */   public Shape clone()
/* 224:    */   {
/* 225:307 */     return new QuadCurveShape(this);
/* 226:    */   }
/* 227:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.QuadCurveShape
 * JD-Core Version:    0.7.0.1
 */