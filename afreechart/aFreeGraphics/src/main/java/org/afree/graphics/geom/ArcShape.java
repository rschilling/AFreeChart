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
/*  13:    */ public class ArcShape
/*  14:    */   implements Shape
/*  15:    */ {
/*  16:    */   private RectF mOval;
/*  17:    */   private Path mPath;
/*  18:    */   private Region mRegion;
/*  19:    */   private float mStartAngle;
/*  20:    */   private float mAngleExtent;
/*  21:    */   private PointF mStartPoint;
/*  22:    */   private PointF mEndPoint;
/*  23:    */   private boolean mUseCenter;
/*  24:    */   
/*  25:    */   public ArcShape()
/*  26:    */   {
/*  27: 47 */     this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public ArcShape(float x, float y)
/*  31:    */   {
/*  32: 56 */     this(x, y, 0.0F, 0.0F, 0.0F, 0.0F);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public ArcShape(float x, float y, float width, float height)
/*  36:    */   {
/*  37: 67 */     this(x, y, width, height, 0.0F, 0.0F);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public ArcShape(float x, float y, float width, float height, float startAngle, float angleExtent)
/*  41:    */   {
/*  42: 80 */     this(x, y, width, height, startAngle, angleExtent, true);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public ArcShape(float x, float y, float width, float height, float startAngle, float angleExtent, boolean useCenter)
/*  46:    */   {
/*  47: 94 */     this.mOval = new RectF(x, y, x + width, y + height);
/*  48: 95 */     this.mStartAngle = startAngle;
/*  49: 96 */     this.mAngleExtent = angleExtent;
/*  50: 97 */     this.mPath = new Path();
/*  51: 98 */     this.mStartPoint = new PointF();
/*  52: 99 */     this.mEndPoint = new PointF();
/*  53:100 */     this.mUseCenter = useCenter;
/*  54:101 */     this.mRegion = new Region();
/*  55:102 */     update();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public ArcShape(double x, double y, double width, double height, double startAngle, double angleExtent, boolean useCenter)
/*  59:    */   {
/*  60:116 */     this.mOval = new RectF((float)x, (float)y, (float)(x + width), (float)(y + height));
/*  61:117 */     this.mStartAngle = ((float)startAngle);
/*  62:118 */     this.mAngleExtent = ((float)angleExtent);
/*  63:119 */     this.mPath = new Path();
/*  64:120 */     this.mStartPoint = new PointF();
/*  65:121 */     this.mEndPoint = new PointF();
/*  66:122 */     this.mUseCenter = useCenter;
/*  67:123 */     update();
/*  68:    */   }
/*  69:    */   
/*  70:    */   public ArcShape(RectShape rect, float startAngle, float angleExtent, boolean useCenter)
/*  71:    */   {
/*  72:134 */     this(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), startAngle, angleExtent, useCenter);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public ArcShape(RectShape rect, double startAngle, double angleExtent, boolean useCenter)
/*  76:    */   {
/*  77:145 */     this(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), (float)startAngle, (float)angleExtent, useCenter);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public ArcShape(ArcShape arcShape)
/*  81:    */   {
/*  82:153 */     this.mOval = arcShape.mOval;
/*  83:154 */     this.mStartAngle = arcShape.mStartAngle;
/*  84:155 */     this.mAngleExtent = arcShape.mAngleExtent;
/*  85:156 */     this.mStartPoint = new PointF(arcShape.mStartPoint.x, arcShape.mStartPoint.y);
/*  86:157 */     this.mEndPoint = new PointF(arcShape.mEndPoint.x, arcShape.mEndPoint.y);
/*  87:158 */     this.mPath = arcShape.mPath;
/*  88:159 */     this.mRegion = arcShape.mRegion;
/*  89:160 */     this.mUseCenter = arcShape.mUseCenter;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Path getPath()
/*  93:    */   {
/*  94:164 */     return this.mPath;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void draw(Canvas canvas, Paint paint)
/*  98:    */   {
/*  99:171 */     paint.setStyle(Paint.Style.STROKE);
/* 100:172 */     canvas.drawArc(this.mOval, this.mStartAngle, this.mAngleExtent, this.mUseCenter, paint);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void fill(Canvas canvas, Paint paint)
/* 104:    */   {
/* 105:179 */     paint.setStyle(Paint.Style.FILL);
/* 106:180 */     canvas.drawArc(this.mOval, this.mStartAngle, this.mAngleExtent, this.mUseCenter, paint);
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/* 110:    */   {
/* 111:187 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/* 112:188 */     canvas.drawArc(this.mOval, this.mStartAngle, this.mAngleExtent, this.mUseCenter, paint);
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void clip(Canvas canvas)
/* 116:    */   {
/* 117:195 */     canvas.clipPath(this.mPath);
/* 118:    */   }
/* 119:    */   
/* 120:    */   public boolean contains(RectShape rect)
/* 121:    */   {
/* 122:202 */     return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 123:    */   }
/* 124:    */   
/* 125:    */   public boolean contains(float x, float y)
/* 126:    */   {
/* 127:209 */     return this.mRegion.contains((int)x, (int)y);
/* 128:    */   }
/* 129:    */   
/* 130:    */   public boolean contains(float x, float y, float width, float height)
/* 131:    */   {
/* 132:216 */     Region tmpRegion = new Region();
/* 133:217 */     Region targetRegion = new Region((int)x, (int)y, (int)(width + x), (int)(height + y));
/* 134:218 */     tmpRegion.op(this.mRegion, targetRegion, Region.Op.INTERSECT);
/* 135:219 */     return !targetRegion.op(tmpRegion, Region.Op.XOR);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean contains(PointF point)
/* 139:    */   {
/* 140:226 */     return this.mRegion.contains((int)point.x, (int)point.y);
/* 141:    */   }
/* 142:    */   
/* 143:    */   @Deprecated
/* 144:    */   public RectShape getBounds()
/* 145:    */   {
/* 146:234 */     return new RectShape(this.mRegion.getBounds());
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void getBounds(RectShape rect)
/* 150:    */   {
/* 151:241 */     rect.setRect(this.mRegion.getBounds());
/* 152:    */   }
/* 153:    */   
/* 154:    */   public boolean intersects(float x, float y, float width, float height)
/* 155:    */   {
/* 156:248 */     Region region = new Region(this.mRegion);
/* 157:    */     float tmpw;
/* 158:    */     float tmpx;
/* 159:    */     
/* 160:251 */     if (x < width + x)
/* 161:    */     {
/* 162:252 */       tmpx = x;
/* 163:253 */       tmpw = width;
/* 164:    */     }
/* 165:    */     else
/* 166:    */     {
/* 167:256 */       tmpx = width + x;
/* 168:257 */       tmpw = Math.abs(width);
/* 169:    */     }
/* 170:    */     float tmph;
/* 171:    */     float tmpy;
/* 172:    */     
/* 173:259 */     if (y < height + y)
/* 174:    */     {
/* 175:260 */       tmpy = y;
/* 176:261 */       tmph = height;
/* 177:    */     }
/* 178:    */     else
/* 179:    */     {
/* 180:264 */       tmpy = height + y;
/* 181:265 */       tmph = Math.abs(height);
/* 182:    */     }
/* 183:267 */     return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 184:    */   }
/* 185:    */   
/* 186:    */   public boolean intersects(Rect rect)
/* 187:    */   {
/* 188:274 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 189:    */   }
/* 190:    */   
/* 191:    */   public boolean intersects(RectShape rect)
/* 192:    */   {
/* 193:281 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void translate(float x, float y)
/* 197:    */   {
/* 198:288 */     this.mOval.left += x;
/* 199:289 */     this.mOval.right += x;
/* 200:290 */     this.mOval.top += y;
/* 201:291 */     this.mOval.bottom += y;
/* 202:292 */     update();
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void setArc(RectShape arcRect, float startAngle, float antleExtent, boolean useCenter)
/* 206:    */   {
/* 207:303 */     this.mOval = new RectF(arcRect.getRectF());
/* 208:304 */     this.mStartAngle = startAngle;
/* 209:305 */     this.mAngleExtent = antleExtent;
/* 210:306 */     this.mUseCenter = useCenter;
/* 211:307 */     update();
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setArc(RectShape arcRect, double startAngle, double angleExtent, boolean useCenter)
/* 215:    */   {
/* 216:318 */     this.mOval = new RectF(arcRect.getRectF());
/* 217:319 */     this.mStartAngle = ((float)startAngle);
/* 218:320 */     this.mAngleExtent = ((float)angleExtent);
/* 219:321 */     this.mUseCenter = useCenter;
/* 220:322 */     update();
/* 221:    */   }
/* 222:    */   
/* 223:    */   public float getX()
/* 224:    */   {
/* 225:330 */     return this.mOval.left;
/* 226:    */   }
/* 227:    */   
/* 228:    */   public float getY()
/* 229:    */   {
/* 230:338 */     return this.mOval.top;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public float getWidth()
/* 234:    */   {
/* 235:346 */     return this.mOval.width();
/* 236:    */   }
/* 237:    */   
/* 238:    */   public float getHeight()
/* 239:    */   {
/* 240:354 */     return this.mOval.height();
/* 241:    */   }
/* 242:    */   
/* 243:    */   public float getMinX()
/* 244:    */   {
/* 245:362 */     return this.mOval.left;
/* 246:    */   }
/* 247:    */   
/* 248:    */   public float getMaxX()
/* 249:    */   {
/* 250:370 */     return this.mOval.right;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public float getMinY()
/* 254:    */   {
/* 255:378 */     return this.mOval.top;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public float getMaxY()
/* 259:    */   {
/* 260:386 */     return this.mOval.bottom;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public float getCenterX()
/* 264:    */   {
/* 265:394 */     return this.mOval.centerX();
/* 266:    */   }
/* 267:    */   
/* 268:    */   public float getCenterY()
/* 269:    */   {
/* 270:402 */     return this.mOval.centerY();
/* 271:    */   }
/* 272:    */   
/* 273:    */   public float getAngleStart()
/* 274:    */   {
/* 275:410 */     return this.mStartAngle;
/* 276:    */   }
/* 277:    */   
/* 278:    */   public void setAngleStart(float degree)
/* 279:    */   {
/* 280:418 */     this.mStartAngle = degree;
/* 281:419 */     update();
/* 282:    */   }
/* 283:    */   
/* 284:    */   public float getAngleExtent()
/* 285:    */   {
/* 286:427 */     return this.mAngleExtent;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public void setAngleExtent(float degree)
/* 290:    */   {
/* 291:435 */     this.mAngleExtent = degree;
/* 292:436 */     update();
/* 293:    */   }
/* 294:    */   
/* 295:    */   public PointF getStartPoint()
/* 296:    */   {
/* 297:444 */     return new PointF(this.mStartPoint.x, this.mStartPoint.y);
/* 298:    */   }
/* 299:    */   
/* 300:    */   public PointF getEndPoint()
/* 301:    */   {
/* 302:452 */     return new PointF(this.mEndPoint.x, this.mEndPoint.y);
/* 303:    */   }
/* 304:    */   
/* 305:    */   public boolean isUseCenter()
/* 306:    */   {
/* 307:460 */     return this.mUseCenter;
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void setUseCenter(boolean useCenter)
/* 311:    */   {
/* 312:468 */     this.mUseCenter = useCenter;
/* 313:    */   }
/* 314:    */   
/* 315:    */   private void update()
/* 316:    */   {
/* 317:475 */     this.mPath.reset();
/* 318:    */     
/* 319:477 */     this.mStartPoint.x = ((float)(Math.sin(Math.toRadians(this.mStartAngle + 90.0D)) * (this.mOval.width() / 2.0D)) + getCenterX());
/* 320:478 */     this.mStartPoint.y = ((float)(-Math.cos(Math.toRadians(this.mStartAngle + 90.0D)) * (this.mOval.height() / 2.0D)) + getCenterY());
/* 321:479 */     this.mEndPoint.x = ((float)(Math.sin(Math.toRadians(this.mStartAngle + this.mAngleExtent + 90.0D)) * (this.mOval.width() / 2.0D)) + getCenterX());
/* 322:480 */     this.mEndPoint.y = ((float)(-Math.cos(Math.toRadians(this.mStartAngle + this.mAngleExtent + 90.0D)) * (this.mOval.height() / 2.0D)) + getCenterY());
/* 323:    */     
/* 324:482 */     this.mPath.arcTo(this.mOval, this.mStartAngle, this.mAngleExtent);
/* 325:483 */     if (this.mUseCenter) {
/* 326:484 */       this.mPath.lineTo(this.mOval.centerX(), this.mOval.centerY());
/* 327:    */     }
/* 328:486 */     this.mPath.close();
/* 329:487 */     this.mRegion = new Region((int)this.mOval.left, (int)this.mOval.top, (int)this.mOval.right, (int)this.mOval.bottom);
/* 330:488 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 331:    */   }
/* 332:    */   
/* 333:    */   public boolean equals(Object o)
/* 334:    */   {
/* 335:496 */     if ((o instanceof ArcShape))
/* 336:    */     {
/* 337:497 */       ArcShape arcShape = (ArcShape)o;
/* 338:498 */       if ((this.mOval.left == arcShape.mOval.left) && 
/* 339:499 */         (this.mOval.right == arcShape.mOval.right) && 
/* 340:500 */         (this.mOval.top == arcShape.mOval.top) && 
/* 341:501 */         (this.mOval.bottom == arcShape.mOval.bottom) && 
/* 342:502 */         (this.mStartAngle == arcShape.mStartAngle) && 
/* 343:503 */         (this.mAngleExtent == arcShape.mAngleExtent) && 
/* 344:504 */         (this.mUseCenter == arcShape.mUseCenter)) {
/* 345:506 */         return true;
/* 346:    */       }
/* 347:    */     }
/* 348:509 */     return false;
/* 349:    */   }
/* 350:    */   
/* 351:    */   public Shape clone()
/* 352:    */   {
/* 353:517 */     return new ArcShape(this);
/* 354:    */   }
/* 355:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.ArcShape
 * JD-Core Version:    0.7.0.1
 */