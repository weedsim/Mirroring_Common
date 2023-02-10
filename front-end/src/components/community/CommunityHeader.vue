<template>

  <!-- 헤드 -->
  <div class="m-0 p-0 row" style="height: 100%">
    <div class="m-0 p-0 col-9">
      <h1>커뮤니티</h1>

      
    </div>


    <div class="m-0 p-0 col-3">

      <div class="m-0 p-0 row" style="height: 100%"   >
        <div class="m-0 p-0 col-3">
          <div class="form"> 
            <select class="m-0 p-0 form-select-sm" id="floatingSelect" aria-label="Floating label select example" style="height: 37px;">
              <option selected style="text-align: center;" >제목</option>
              <option value="2" style="text-align: center;">작성자</option>
            </select>
          </div>
        </div>
        <div class="m-0 p-0 col-9">
          <div class="wrap">
            <!-- <div class="search">
              <input type="text" class="searchTerm" placeholder="검색창" style="height: auto;"  aria-describedby="button-addon3">
                <button type="submit" class="searchButton">
                  <i class="fa fa-search" id="button-addon3"></i>
                </button>
            </div> -->
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="댓글을 입력하세요" aria-describedby="button-add" style="height: 37px;">
              <button type="submit" class="searchButton" id="button-add" >
                <i class="fa fa-search" id="button-add" ></i>
              </button>
            </div> 
          </div>  
        </div>
      </div>
      
     
    </div>
      
                
  </div>
                    <!--         
              <div class="m-0 p-0 row">
                <table class="table" style="color:white">
                  <thead>
                    <tr>
                      <th scope="col">제목</th>
                      <th scope="col">작성자</th>
                      <th scope="col">조회수</th>
                      <th scope="col">시간</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="row"><BIconBellFill></BIconBellFill>공지합니다!</th>
                      <td>운영자1</td>
                      <td>539</td>
                      <td>14분전</td>
                    </tr>
                    <tr>
                      <th scope="row"><BIconBellFill></BIconBellFill>필독! 가입 시 중요사항</th>
                      <td>운영자2</td>
                      <td>622</td>
                      <td>4시간전</td>
                    </tr>
                    <tr>
                      <th scope="row"> 운영자님 부탁드립니다</th>
                      <td>호에에엥</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>엘사</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>미구엘</td>
                      <td>11</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>증권사</td>
                      <td>6</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>증권사</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>증권사</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>증권사</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr>
                    <tr>
                      <th scope="row">폰게임 할만한거 추천좀 받을수 있나요</th>
                      <td>증권사</td>
                      <td>32</td>
                      <td>2023.01.30</td>
                    </tr> 
                    
                  </tbody>
                </table>
                      <div class="m-0 p-0 col-11">
                          <nav aria-label="Page navigation example" id="nav_page" style=" text-align: center;">
                          <ul class="pagination" style=" display:inline-flex;">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                            
                          </ul>
                        </nav>
                      </div>
                      <div class="m-0 p-0 col-1">
                        <a class="btn btn-secondary" href="./CreateArticle.vue" >글Tmrl</a>
                      </div>
                    
              </div>
              
              
              
            </div>
              
          </div>
          게시글
          
          
          푸터
          
        </div>
      </div>
      <div class="m-0 p-0 col-2">

      </div>
    </div> -->
</template>

<script>

export default {
  
  components: {  },
  name: "CommunityHeader",
  data() {
    return {
      
    };
  },
  created() {
    // 받아올 메서드 이름 한번은(ex)this.userinfo )
  },
  methods: {
    
    
  },
}
</script>

<style scoped>
</style>
