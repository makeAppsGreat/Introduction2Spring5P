package chap07;

public class ExeTimeCalculator implements Calculator {
  
  private Calculator delegate;
  
  public ExeTimeCalculator(Calculator delegate) { this.delegate = delegate; }
  
  @Override
  public long factorial(long num) {
    long start = System.nanoTime();
    long result = delegate.factorial(num);
    
    System.out.printf("%s.factorial(%d) 실행 시간 = %d", delegate.getClass().getSimpleName(), num, System.nanoTime() - start);
    return result;
  }
  
}
