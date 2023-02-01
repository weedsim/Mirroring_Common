<template>
  <navbar />
  <div class="m-0 p-0 maincommunity" >
    <div class="m-0 p-0 row">
      <div class="m-0 p-0 col-2">
        <h1>dddd</h1>
        
        
        
      </div>
      <div class="m-0 p-0 col-8">
        <!-- 메인커뮤니티 -->
          <div style="width: 100%; height:95vh;" >
            <!-- <h1>main</h1> -->
            <!-- 헤드 -->
            <div class="m-0 p-0 row">
              <div class="m-0 p-0 col-3">
                <h1>커뮤니티</h1>

                  

                
                
                
                <!-- <div style="right" class="input-group">
                  <h1>커뮤니티</h1> 
                  <div class="form-outline">
                    <input type="search" id="form1" class="form-control"/>
                    <label class="form-label" for="form1">Search</label>
                  </div>
                  <button type="button" class="btn btn-primary">
                    <i class="fas fa-search"></i>
                  </button>
                </div> -->
              </div>
              <div class="m-0 p-0 col-3">
                asd
                    </div>
              <div class="m-0 p-0 col-6">
                <div class="dropdown">
                <button
                  class="btn btn-primary dropdown-toggle"
                  type="button"
                  id="dropdownMenuButton"
                  data-mdb-toggle="dropdown"
                  aria-expanded="false"
                >
                  Dropdown button
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li><a class="dropdown-item" href="#">Something else here</a></li>
                </ul>
              </div>
                
                <div class="wrap">
                  <div class="search">
                      <input type="text" class="searchTerm" placeholder="What are you looking for?">
                      <button type="submit" class="searchButton">
                        <i class="fa fa-search"></i>
                    </button>
                  </div>
                </div>
                    </div>
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
                      <th scope="row">운영자님 부탁드립니다</th>
                      <td>호에에엥</td>
                      <td>9</td>
                      <td>14분전</td>
                    </tr>
                    <tr>
                      <th scope="row">완전 처음인 뉴비입니다</th>
                      <td>빈따리</td>
                      <td>22</td>
                      <td>4시간전</td>
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
                    <nav aria-label="Page navigation example" id="nav_page" style=" text-align: center;">
                      <ul class="pagination" style=" display:inline-flex;">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        <button type="button" class="btn btn-secondary btn-sm" >글쓰기</button>
                      
                      </ul>
                    </nav>
              
                    
              </div>
              
              
              
            </div>
              <!-- <hr /> -->
          </div>
          <!-- 게시글 -->
          
          
          <!-- <div class="m-0 p-0 row">
            
          </div> -->

          <!-- 푸터 -->
          <!-- <div class="m-0 p-0 row"></div> -->
        </div>
      </div>
      <div class="m-0 p-0 col-2">

      </div>
    </div>
  
</template>

<script>
import navbar from "@/components/NavView.vue";

export default {
  name: "Community",
  components: { navbar },
  data() {
    return {
      
    }
  },
  setup() {},
  created() {},
  mounted() {},
  methods: {
    fnGetList() {
      this.list = [
        {
            "idx":1,
            "title": "제목1",
            "author": "작성자1",
            "created_at": "작성일시1"
        },
        {
            "idx":1,
            "title": "제목1",
            "author": "작성자1",
            "created_at": "작성일시1"
        },
        {
            "idx":1,
            "title": "제목1",
            "author": "작성자1",
            "created_at": "작성일시1"
        }
      ]
    }
  },
};
</script>

<style scoped>
.maincommunity {
  background-image: url(../../assets/lobby/lobby.jpg);
  background-repeat: no-repeat;
  background-size:cover;
  color: white;
}
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid #00B4CC;
  border-right: none;
  /* padding: 5px; */
  /* height: 20px;  */
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
}

.searchTerm:focus{
  color: #00B4CC;
}

.searchButton {
  width: 40px;
  height: 36px;
  border: 1px solid #00B4CC;
  background: #00B4CC;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}

/*Resize the wrap to see the search bar change!*/

</style>

